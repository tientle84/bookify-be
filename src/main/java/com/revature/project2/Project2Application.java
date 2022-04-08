package com.revature.project2;

import com.revature.project2.Utility.DataPopulateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project2Application {

	@Autowired
	private DataPopulateUtility util;

	public static void main(String[] args) {
		System.out.println(System.getenv("db_password"));
		SpringApplication.run(Project2Application.class, args);
	}

	@Bean
	public CommandLineRunner test(){
		return s -> {
			util.populateInitialData();
		};
	}

}
