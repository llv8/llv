package com.llv.sample.dubbo.provider;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {

	@Override
	public User getUser(Long id) {
		User user = new User();
		user.setId(id);
		user.setName("test");
		user.setAge(18);
		return user;
	}

}
