package com.parsons.elliott.springboot.garage.mySpringBootGarageApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MySpringBootGarageAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootGarageAppApplication.class, args);
	}
}
