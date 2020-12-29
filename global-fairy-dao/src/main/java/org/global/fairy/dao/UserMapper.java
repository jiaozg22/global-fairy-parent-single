package org.global.fairy.dao;

import org.global.fairy.modules.dao.User;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

	User getUser();

	int insertUser(User user);

	int deleteUser(long id);

	/**
	 * @Autor   jiaozg
	 * @param
	 * @return
	 */
	List<User> queryList(User user);
}
