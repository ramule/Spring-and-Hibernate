package com.niit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.Model.User;

@Controller
public class IndexController{
	@RequestMapping(value = {"/home","/"})
	public String m1() {
		return "index";
	}
	
	@RequestMapping(value = {"/register"})
	public String m2(ModelMap map) {
		map.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value = {"/login"})
	public String m3() {
		return "login";
	}
	
	@RequestMapping(value = {"/logout"})
	public String m4() {
		return "login";
	}
}