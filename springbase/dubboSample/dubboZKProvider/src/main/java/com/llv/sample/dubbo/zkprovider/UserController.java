package com.llv.sample.dubbo.zkprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Value("${server.port}")
	private int port;

	@RequestMapping("/")
	public int getPort() {
		return port;
	}
}
