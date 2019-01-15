package com.ddou.util;

import org.apache.commons.lang3.RandomStringUtils;

public class StrUtil {
	public static String randomNum(int len) {
		return RandomStringUtils.randomNumeric(len);
	}

	public static String randomNum() {
		return randomNum(6);
	}
}
