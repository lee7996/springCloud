package com.javbus.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SetTest {

	public static void main(String[] args) {
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		set1.add("1");
		set1.add("2");
		set2.add("1");
		set2.add("2");
		System.out.println(set1.equals(set2));

		Map<String, String> map = new HashMap<>(5);
		System.out.println("befor: " + map.size());
		System.out.println("befor: " + map.size());
		System.out.println("befor: " + map.size());
		map.put("1", "value1");
		System.out.println("after: " + map.size());
		map.put("2", "value2");
		map.put("3", "value3");
		map.put("4", "value4");
		map.put(null, null);
		AtomicInteger integer = new AtomicInteger(10);
		int x = integer.addAndGet(5);
	}
}
