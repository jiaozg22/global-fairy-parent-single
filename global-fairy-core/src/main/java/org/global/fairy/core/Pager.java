package org.global.fairy.core;

import java.io.Serializable;

/**
 * 分页参数接收
 * 
 * @author jiao
 * 
 */
public class Pager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer defaultPage;// 当前页码数
	private Integer defaultPageSize;// 每页条数
	private Boolean defaultUseFlag;// 是否启用插件
	private Boolean defaultCheckFlag;// 是否检测当前页码的有效性

	public Integer getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(Integer defaultPage) {
		this.defaultPage = defaultPage;
	}

	public Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(Integer defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public Boolean getDefaultUseFlag() {
		return defaultUseFlag;
	}

	public void setDefaultUseFlag(Boolean defaultUseFlag) {
		this.defaultUseFlag = defaultUseFlag;
	}

	public Boolean getDefaultCheckFlag() {
		return defaultCheckFlag;
	}

	public void setDefaultCheckFlag(Boolean defaultCheckFlag) {
		this.defaultCheckFlag = defaultCheckFlag;
	}

}
