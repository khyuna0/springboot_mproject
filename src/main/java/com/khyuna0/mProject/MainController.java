package com.khyuna0.mProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.model.Model;

@Controller
public class MainController {
	
	@GetMapping(value ="/")
	@ResponseBody
	public String root(Model model) {
		return "index";
	}
}
