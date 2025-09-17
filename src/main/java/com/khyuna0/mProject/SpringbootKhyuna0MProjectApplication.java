package com.khyuna0.mProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class SpringbootKhyuna0MProjectApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringbootKhyuna0MProjectApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootKhyuna0MProjectApplication.class, args);
	}
	
	
}
