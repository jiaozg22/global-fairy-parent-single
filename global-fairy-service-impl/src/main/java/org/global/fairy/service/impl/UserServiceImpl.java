package org.global.fairy.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.dao.UserMapper;
import org.global.fairy.modules.dao.User;
import org.global.fairy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService{
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void sayHello(User user) {
		
		
		
		logger.info("into service");
		
		userMapper.insertUser(user);
		
	}

}
