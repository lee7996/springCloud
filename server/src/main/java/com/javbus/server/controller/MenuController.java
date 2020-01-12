package com.javbus.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javbus.server.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuan
 * @since 2019-10-18
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	
	@Autowired
	MenuService menuService;
	
	@GetMapping("/getMenuTree")
	@ResponseBody
	public Object getMenuTree() {
		
		return menuService.getMenuTree();
	}
	
}
