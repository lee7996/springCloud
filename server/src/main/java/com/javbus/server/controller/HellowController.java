package com.javbus.server.controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HellowController {
	
	@RequestMapping("/say")
	public Object say() {
		return "hellow !!";
	}
	
	public static void main(String[] args) {

	Stream.of("one", "two", "three", "four")
	     .filter(e -> e.length() > 3)
	     .peek(e -> System.out.println("Filtered value: " + e))
	     .map(String::toUpperCase)
	     .peek(e -> System.out.println("Mapped value: " + e))
	     .collect(Collectors.toList());
	}
}
