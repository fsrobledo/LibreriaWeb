package com.ArgenProg.TPFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TpFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpFinalApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "Argentina Programa") String name) {
		return String.format("Hola %s!", name);
	}

}
