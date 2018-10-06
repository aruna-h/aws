package com.bridgelabz.s3;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class S3bucketserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3bucketserviceApplication.class, args);
	}
}
