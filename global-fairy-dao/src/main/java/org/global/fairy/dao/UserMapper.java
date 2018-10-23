package org.global.fairy.dao;

import org.global.fairy.modules.dao.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	public User getUser();

	public int insertUser(User user);

	public int deleteUser(long id);

}
