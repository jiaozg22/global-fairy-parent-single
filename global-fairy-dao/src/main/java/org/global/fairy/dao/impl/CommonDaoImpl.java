package org.global.fairy.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.global.fairy.dao.CommonDao;
import org.global.fairy.dao.beans.Po;
import org.global.fairy.dao.params.WherePrams;

public class CommonDaoImpl implements CommonDao {

	@Override
	public long nextId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addLocal(Po po) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Po po) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Po get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getField(Serializable id, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateLocal(Po po) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Po po) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLocal(Po po, WherePrams where) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Po po, WherePrams where) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExist(Po po) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Po get(WherePrams where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getFile(WherePrams where, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list(WherePrams where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable[] listFile(WherePrams where, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List listFiles(WherePrams where, String[] files) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int del(WherePrams where) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List listBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int excuse(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(WherePrams where) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExist(WherePrams where) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List in(String fileName, Serializable[] values) {
		// TODO Auto-generated method stub
		return null;
	}

}
