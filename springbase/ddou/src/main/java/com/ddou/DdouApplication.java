package com.ddou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ddou.util.SMSUtil;

@SpringBootApplication
public class DdouApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdouApplication.class, args);
		SMSUtil.register("18161907873");
	}
}
