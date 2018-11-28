package com.isoftstone.pmit.common.util;

import java.util.List;

public class ListUtils {
	public static <T> boolean isEmpty(List<T> list) {
		return null == list || list.isEmpty();
	}
}
