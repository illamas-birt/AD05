package eus.birt.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ad05Controller {
	@GetMapping("/")
	public String inicio() {
		return "index";
	}
}
