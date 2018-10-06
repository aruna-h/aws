package com.bridgelabz.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DockerProjApplication {
	@GetMapping("/")
	public String getMessage()
	{
		return "welcome to docker!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DockerProjApplication.class, args);
	}
	
}
