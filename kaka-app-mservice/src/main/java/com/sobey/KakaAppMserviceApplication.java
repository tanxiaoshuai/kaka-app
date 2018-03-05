package com.sobey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class KakaAppMserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaAppMserviceApplication.class, args);
	}

}
