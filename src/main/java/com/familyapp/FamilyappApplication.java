package com.familyapp;

import com.familyapp.models.filters.StatsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FamilyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyappApplication.class, args);
	}


	@Bean
	public StatsFilter simpleFilter() {
		return new StatsFilter();
	}
}
