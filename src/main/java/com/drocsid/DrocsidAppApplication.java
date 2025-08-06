package com.drocsid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DrocsidAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrocsidAppApplication.class, args);
	}

}
