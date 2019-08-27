package org.global.faiy.web.form;

import java.io.Serializable;

public class AddUserForm implements Serializable{
	
	public int id;

	public String codeId;

	public String username;

	public String password;

	public int roleId;

	public String regtime;

	public String lastlogtime;

	public int logcounts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getLastlogtime() {
		return lastlogtime;
	}

	public void setLastlogtime(String lastlogtime) {
		this.lastlogtime = lastlogtime;
	}

	public int getLogcounts() {
		return logcounts;
	}

	public void setLogcounts(int logcounts) {
		this.logcounts = logcounts;
	}
	

}
