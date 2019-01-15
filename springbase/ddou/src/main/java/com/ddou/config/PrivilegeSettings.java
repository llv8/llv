package com.ddou.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:privilege.properties")
@ConfigurationProperties()
@Configuration
public class PrivilegeSettings {
	public PrivilegeSettings() {
		System.out.println("PrivilegeSettings");
	}

	@Value("ali.sms.accesskey.id")
	private String smsKeyId;
	@Value("ali.sms.accesskey.secret")
	private String smsKeySecret;

	public String getSmsKeyId() {
		return smsKeyId;
	}

	public String getSmsKeySecret() {
		return smsKeySecret;
	}

}
