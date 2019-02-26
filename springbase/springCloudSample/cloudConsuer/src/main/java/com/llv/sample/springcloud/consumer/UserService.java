package com.llv.sample.springcloud.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "userService", url = "http://localhost:8888/user")
public interface UserService {
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = "application/json")
	public User getUser(@PathVariable("id") Long id);
}
