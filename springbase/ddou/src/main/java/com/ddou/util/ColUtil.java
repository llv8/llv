package com.ddou.util;

import java.util.HashMap;
import java.util.Map;

public class ColUtil {
	public static <K, V> Map<K, V> createMap() {
		return new HashMap<>();
	}

	public static <K, V> Map<K, V> createMap(K key, V value) {
		Map<K, V> map = createMap();
		map.put(key, value);
		return map;
	}

}
