package com.javatechie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DevopsIntegrationApplication {

	@GetMapping
	public String message(){
<<<<<<< HEAD
		return "welcome to devops-pipeline -zineb dr";
=======
		return "welcome to devops-pipeline  zineb   hi ";
>>>>>>> 5b2448e76eaa2d8264114f8d345569fc7195bcce
	}

	public static void main(String[] args) {
		SpringApplication.run(DevopsIntegrationApplication.class, args);
	}

}
