package com.javbus.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowController {
	
	@RequestMapping("/say")
	public Object say() {
		return "hellow !!";
	}
}
