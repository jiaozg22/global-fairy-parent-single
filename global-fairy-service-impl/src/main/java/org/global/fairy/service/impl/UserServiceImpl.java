package org.global.fairy.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.dao.UserMapper;
import org.global.fairy.modules.dao.User;
import org.global.fairy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Autor jiao_zg22
 * @Date 2019-08-29 23:11:00
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean sayHello(User user) {
		return userMapper.insertUser(user) > 0 ? true : false;
	}

	@Override
	public List<User> queryList(User user) {
		return userMapper.queryList(user);
	}


}
