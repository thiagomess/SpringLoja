package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String Home() {
		System.out.println("Entrando na Home CDC");
		
		return "home";
		
	}
	
	

}
