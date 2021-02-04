package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.utils.Constants;

@SpringBootApplication
public class TopicApplication {

	private static final Logger logger= LoggerFactory.getLogger(TopicApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(TopicApplication.class, args);

		logger.info(Constants.START);
	}
}
