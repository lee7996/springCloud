package com.javbus.server;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		set1.add("1");
		set1.add("2");
		set2.add("1");
		set2.add("2");
		System.out.println(set1.equals(set2));
	}
}
