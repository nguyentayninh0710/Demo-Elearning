package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {
	@RequestMapping(value = {"", "home"}, method = RequestMethod.GET)
	public String index() {
		return "home/index";
	}
}
