package com.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //jpa auditing 활성화
@SpringBootApplication
public class HealthcareApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(HealthcareApplication.class, args);
	}

}




