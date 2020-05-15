package com.dustin.springbootblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootblogApplication.class, args);
	}

}
