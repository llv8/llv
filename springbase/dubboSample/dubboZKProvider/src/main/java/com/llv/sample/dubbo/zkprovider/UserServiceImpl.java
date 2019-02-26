package com.llv.sample.dubbo.zkprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "${demo.service.version}")
@Component
public class UserServiceImpl implements UserService {
	@Value("${spring.application.name}")
	private String name;

	@Override
	public User getUser(Long id) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setAge(18);
		return user;
	}

}
