package org.global.fairy.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.log4testng.Logger;

public class SqlSessionFactoryUtil {

	// SqlSessionFactory对象
	private static SqlSessionFactory sqlSessionFactory = null;

	// 类线程锁
	private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

	/**
	 * 私有化构造方法
	 */
	private SqlSessionFactoryUtil() {
	}

	public static SqlSessionFactory initSqlSessionFactory() {
		String resource = "classpath*:mybatis-config.xml";
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			Logger.getLogger(SqlSessionFactoryUtil.class).info(e);
		}

		synchronized (CLASS_LOCK) {
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder()
						.build(inputStream);
			}
		}

		return sqlSessionFactory;
	}

	/**
	 * 打开session
	 * 
	 * @return
	 */
	public static SqlSession openSqlSession() {
		if (sqlSessionFactory == null) {
			initSqlSessionFactory();
		}

		return sqlSessionFactory.openSession();
	}

}
