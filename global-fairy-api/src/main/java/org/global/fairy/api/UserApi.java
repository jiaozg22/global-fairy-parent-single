package org.global.fairy.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.modules.dao.User;
import org.global.fairy.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserApi {
	private static final Logger logger = LogManager.getLogger(UserApi.class);
	
	@Resource
	public IUserService userService;

	public void sayHello(String name) {
		logger.info("into api");
		User user = new User();
		
//		user.setId(10000);
		user.setName(name);
		userService.sayHello(user);
	}
	
	public boolean addUser(User user) {
		logger.info("into api");
		
		return  userService.sayHello(user);
	}

	public void registor(String string) {
		// TODO Auto-generated method stub
		
	}

	public List<User> queryList(User user){

		return userService.queryList(user);
	}
}
