package com.ddou.util;

import com.google.gson.Gson;

public class JsonUtil {
	private static Gson createGson() {
		return new Gson();
	}

	public static String toJson(Object o) {
		return createGson().toJson(o);
	}
}
