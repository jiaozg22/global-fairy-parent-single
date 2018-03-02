package org.global.fairy.modules.dao;

import java.io.Serializable;


public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6620577346749833594L;

	/**
	 * 自增id
	 */
	private long id;

	/**
	 * 姓名
	 */
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
