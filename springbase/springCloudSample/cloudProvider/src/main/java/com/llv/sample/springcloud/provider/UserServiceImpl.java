package com.llv.sample.springcloud.provider;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("userService")
@RequestMapping("/user")
public class UserServiceImpl implements UserService {

	@Override
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		User user = new User();
		user.setId(id);
		user.setName("test");
		user.setAge(18);
		return user;
	}

}
