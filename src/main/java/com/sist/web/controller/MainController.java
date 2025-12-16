package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
@Controller
public class MainController {
	@GetMapping("/")
	public String main_page()
	{
		return "index";
	}
}
