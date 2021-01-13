package br.ufscar.dc.dsw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
