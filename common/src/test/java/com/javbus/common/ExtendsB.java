package com.javbus.common;

public class ExtendsB extends ExtendsA {

	static final int x = 12;
	int c = 11;
	
	public ExtendsB() {
		super("name");
	}
	
	public void go() {
		System.out.println(c);
	}
	
//	public void go(final int x) {
//		System.out.println(x);
//	}
	
	public static void go(final int c) {
		System.out.println(c);
		System.out.println(x);
	}
	
	public static void main(String[] args) {
		ExtendsB b = new ExtendsB();
		b.go();
		ExtendsB.go(5);
	}
}
