package com.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@GetMapping("errorinterception")
	public Object errorinterception(){
		System.out.println("========错误========");
		
		return "错误!!";
	}
}
