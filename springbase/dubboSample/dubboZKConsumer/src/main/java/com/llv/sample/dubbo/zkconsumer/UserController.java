package com.llv.sample.dubbo.zkconsumer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.llv.sample.dubbo.zkprovider.User;
import com.llv.sample.dubbo.zkprovider.UserService;

@RestController
public class UserController {
	@Reference(url = "dubbo://127.0.0.1:20880")
	private UserService userService;

	@RequestMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}
}
