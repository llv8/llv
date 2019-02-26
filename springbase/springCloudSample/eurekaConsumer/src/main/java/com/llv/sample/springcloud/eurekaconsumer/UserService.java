package com.llv.sample.springcloud.eurekaconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "eurekaProvider")
public interface UserService {
	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}", consumes = "application/json")
	public User getUser(@PathVariable("id") Long id);
}
