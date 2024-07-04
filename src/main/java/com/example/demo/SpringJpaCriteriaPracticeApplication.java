package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(value="com.example.demo")
@ComponentScan(value="com.example.demo.controller")
@ComponentScan(value="com.example.demo.exceptionhandler")
@ComponentScan(value="com.example.demo.service")
@ComponentScan(value="com.example.demo.security")
@ComponentScan(value="com.example.demo.initializer")
@EnableJpaRepositories(value="com.example.demo.repository")
@EntityScan(value="com.example.demo.model.entity")

@SpringBootApplication
public class SpringJpaCriteriaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaCriteriaPracticeApplication.class, args);
	}
}
