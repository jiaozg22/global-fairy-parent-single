package org.global.fairy.service;

import org.global.fairy.modules.dao.User;

import java.util.List;

public interface IUserService {

	boolean sayHello(User user);

	List<User> queryList(User user);
}
