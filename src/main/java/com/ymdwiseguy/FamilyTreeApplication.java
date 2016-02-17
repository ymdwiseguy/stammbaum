package com.ymdwiseguy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class FamilyTreeApplication {

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(FamilyTreeApplication.class);
		app.setShowBanner(true);
		app.run(args);
	}
}
