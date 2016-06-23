package com.dsinnovators.aauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("---->"+new BCryptPasswordEncoder().encode("admin"));
		SpringApplication.run(App.class, args);
	}
}
