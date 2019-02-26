package com.llv.sample.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}
}
