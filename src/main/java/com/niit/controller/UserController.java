package com.niit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.Model.User;
import com.niit.Operations.UserOperations;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	UserOperations uo = new UserOperations();
	
	@RequestMapping("/add")
	public String addUser(@ModelAttribute("user") User user) {
		uo.add(user);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/display{userid}")
	public String displayUser(@PathVariable("userid") int userid, ModelMap map) {
		User user = uo.displaybyid(userid);
		map.addAttribute("user",user);
		return "user";
	}
	
	@RequestMapping(value = "/display")
	public String displayUser( ModelMap map) {
		map.addAttribute("usrs",uo.display());
		return "users";
	}
	
	@RequestMapping(value = "/edit{userid}")
	public String editUser(@PathVariable("userid") int userid, ModelMap map) {
		User user = uo.displaybyid(userid);
		map.addAttribute("u",user);
		return "register";
	}
	
	@RequestMapping(value = "/delete{userid}")
	public String deleteUser(@PathVariable("userid") int userid, ModelMap map) {
		uo.delete(userid);
		return "redirect:/user/display";
	}
	
	@RequestMapping(value = "/update")
	public String updateUser(@ModelAttribute("u") User u) {
		uo.update(u);
		return "redirect:/home";
	}
}