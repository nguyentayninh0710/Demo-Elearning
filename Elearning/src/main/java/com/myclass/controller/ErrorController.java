package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("error")
public class ErrorController {

	@RequestMapping(value = "403", method = RequestMethod.GET)
	public String forbidden() {
		return "error/403";
	}
}
