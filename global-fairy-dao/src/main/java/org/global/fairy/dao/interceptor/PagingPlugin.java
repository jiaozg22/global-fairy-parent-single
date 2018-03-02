package org.global.fairy.dao.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.global.fairy.modules.PageParams;

/**
 * 分页插件，默认为第一页，每页十行。数据库暂时只支持mysql
 * 
 * @author jiao
 * 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagingPlugin implements Interceptor {
	private Integer defaultPage;// 当前页码数
	private Integer defaultPageSize;// 每页条数
	private Boolean defaultUseFlag;// 是否启用插件
	private Boolean defaultCheckFlag;// 是否检测当前页码的有效性

	@Override
	public Object plugin(Object statementHandler) {
		return Plugin.wrap(statementHandler, this);
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler stmtHandler = getUnProxyObject(invocation);
		MetaObject metaStatementHandler = SystemMetaObject
				.forObject(stmtHandler);
		String sql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		// 不是select 语句
		if (!checkSelect(sql)) {
			return invocation.proceed();
		}
		BoundSql boundSql = (BoundSql) metaStatementHandler
				.getValue("delegate.boundSql");
		Object parameterObject = boundSql.getParameterObject();
		PageParams pageParams = getPageParams(parameterObject);
		// 没有分页参数，不启用插件
		if (pageParams == null) {
			return invocation.proceed();
		}

		// 获取分页参数，获取不到时候使用默认值
		Integer pageNum = pageParams.getPage() == null ? this.defaultPage
				: pageParams.getPage();
		Integer pageSize = pageParams.getPageSize() == null ? this.defaultPageSize
				: pageParams.getPageSize();
		Boolean useFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag
				: pageParams.getUseFlag();
		Boolean checkFlag = pageParams.getCheckFlag() == null ? this.defaultCheckFlag
				: pageParams.getCheckFlag();

		if (!useFlag) {
			// 不使用分页插件
			return invocation.proceed();
		}
		int total = getTotal(invocation, metaStatementHandler, boundSql);
		// 回填总数到分页参数里
		setTotalToPageParams(pageParams, total, pageSize);
		// 检查当前页码的有效性
		checkPage(checkFlag, pageNum, pageParams.getTotalPage());
		// 修改SQL
		return changeSQL(invocation, metaStatementHandler, boundSql, pageNum,
				pageSize);
	}

	/**
	 * 修改当前查询的sql
	 * 
	 * @param invocation
	 * @param metaStatementHandler
	 * @param boundSql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	private Object changeSQL(Invocation invocation,
			MetaObject metaStatementHandler, BoundSql boundSql,
			Integer pageNum, Integer pageSize)
			throws InvocationTargetException, IllegalAccessException,
			SQLException {
		String sql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		// 修改SQL,这里使用的是MySql，如果是其他数据库则需要修改
		String newSql = "select * from (" + sql
				+ ") as $_paging_table limit ?,?";
		metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
		// 相当于调用StatementHandler的prepare方法，预编译了当前SQL并设置原有的参数，但是少了两个分页参数，它返回的是一个PreparedStatement对象
		PreparedStatement ps = (PreparedStatement) invocation.proceed();
		// 设置SQL总参数个数
		int count = ps.getParameterMetaData().getParameterCount();
		// 设置两个分页参数
		ps.setInt(count - 1, (pageNum - 1) * pageSize);
		ps.setInt(count, pageSize);
		return ps;
	}

	/**
	 * 判断当前页码是否大于最大页数
	 * 
	 * @param checkFlag
	 * @param pageNum
	 * @param totalPage
	 * @throws Exception
	 */
	private void checkPage(Boolean checkFlag, Integer pageNum, Integer totalPage)
			throws Exception {

		if (checkFlag) {
			// 检查页码page是否合法
			if (pageNum > totalPage) {
				throw new Exception("查询失败，查询页码【" + pageNum + "】大于总页数【"
						+ totalPage + "】");
			}
		}
	}

	/**
	 * 回填总条数和总分页数到分页参数
	 * 
	 * @param pageParams
	 * @param total
	 * @param pageSize
	 */
	private void setTotalToPageParams(PageParams pageParams, int total,
			Integer pageSize) {
		pageParams.setTotal(total);
		// 计算总页数
		int totalPage = total % pageSize == 0 ? total / pageSize : total
				/ pageSize + 1;
		pageParams.setTotalPage(totalPage);

	}

	/**
	 * 分页插件获取总数的方法
	 * 
	 * @param invocation
	 * @param metaStatementHandler
	 * @param boundSql
	 * @return
	 * @throws SQLException
	 */
	private int getTotal(Invocation invocation,
			MetaObject metaStatementHandler, BoundSql boundSql)
			throws SQLException {
		// 获取当前的mappedStatement
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
				.getValue("delegate.mappedStatement");
		// 配置对象
		Configuration cfg = mappedStatement.getConfiguration();
		// 当前需要执行的SQL
		String sql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		// 改写为统计总数的SQL,这里是MySql数据库，如果是其他数据库，需要按照数据库的SQL规范写
		System.out.println(sql);
		String countSql = "select count(*) as total from (" + sql
				+ ") as $_paging";
		// 获取拦截方法参数，我们知道是Connection对象
		Connection connection = (Connection) invocation.getArgs()[0];
		PreparedStatement ps = null;
		int total = 0;
		try {
			// 预编译统计总数SQL
			ps = connection.prepareStatement(countSql);
			// 构建统计总数SQL
			BoundSql couBoundSql = new BoundSql(cfg, countSql,
					boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			// 构建MyBatis的ParameterHandler用来设置总数SQL的参数
			ParameterHandler handler = new DefaultParameterHandler(
					mappedStatement, boundSql.getParameterObject(), boundSql);
			// 设置总数SQL参数
			handler.setParameters(ps);
			// 执行查询
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 这里不能关闭Connection，否则后续的SQL就没法继续了
			if (ps != null) {
				ps.close();
			}
		}
		System.out.println("总条数：" + total);
		return total;
	}

	/**
	 * 判断是否是select 语句
	 * 
	 * @param sql
	 * @return
	 */
	private boolean checkSelect(String sql) {
		String trimSql = sql.trim();
		int idx = trimSql.toLowerCase().indexOf("select");
		return idx == 0;
	}

	/**
	 * 获取分页参数 分解分页参数，这里支持使用Map和@Param注解传递参数，或者POJO继承PageParams，这三种方式都是允许的 @
	 * 
	 * @param parameterObject
	 *            --sql允许参数
	 * 
	 * @return 分页参数
	 */
	private PageParams getPageParams(Object parameterObject) {
		if (parameterObject == null) {
			return null;
		}
		PageParams pageParams = null;
		// 支持Map参数和Mybatis的@Param注解参数
		if (parameterObject instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
			Set<String> keySet = paramMap.keySet();
			Iterator<String> it = keySet.iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = paramMap.get(key);
				if (value instanceof PageParams) {// 继承方式
					return (PageParams) value;
				}
			}
		} else if (parameterObject instanceof PageParams) {// 继承方式
			pageParams = (PageParams) parameterObject;
		}
		return pageParams;
	}

	/**
	 * 从代理对象中分离出真实对象
	 * 
	 * @param invocation
	 * @return 非代理StatementHandler
	 */
	private StatementHandler getUnProxyObject(Invocation invocation) {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();

		MetaObject metaStatementHandler = SystemMetaObject
				.forObject(statementHandler);

		// 分离代理对象链（由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可以分离出最原始的目标类）
		Object object = null;
		while (metaStatementHandler.hasGetter("h")) {
			object = metaStatementHandler.getValue("h");
		}
		if (object == null) {
			return statementHandler;
		}
		return (StatementHandler) object;
	}

	@Override
	public void setProperties(Properties properties) {
		String strDefaultPage = properties.getProperty("default.page", "1");

		String strDefaultPageSize = properties.getProperty("default.pageSize",
				"10");
		String strDefaultUseFlag = properties.getProperty("default.useFlag",
				"true");
		String strDefaultCheckFlag = properties.getProperty(
				"default.checkFlag", "true");

		this.defaultPage = Integer.parseInt(strDefaultPage);
		this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
		this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
		this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);

	}
}
