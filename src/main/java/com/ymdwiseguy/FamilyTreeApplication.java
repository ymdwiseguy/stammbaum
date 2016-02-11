package com.ymdwiseguy;

import com.ymdwiseguy.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        WebConfiguration.class
})
public class FamilyTreeApplication {

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(FamilyTreeApplication.class);
		app.setShowBanner(true);
		app.run(args);
	}
}
