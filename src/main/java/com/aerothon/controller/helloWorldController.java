package com.aerothon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorldController {

	@RequestMapping("/helloWorld")
	public String helloWorld() {
		return "HelloWorld!!";
	}
}
