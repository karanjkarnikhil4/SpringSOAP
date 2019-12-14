package com.nikhilkaranjkar.webservices.springsoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nikhilkaranjkar","karanjkar.nikhil"})
public class SpringsoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsoapApplication.class, args);
	}

}
