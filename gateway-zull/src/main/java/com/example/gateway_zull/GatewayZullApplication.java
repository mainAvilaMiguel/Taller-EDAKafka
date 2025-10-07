package com.example.gateway_zull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayZullApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayZullApplication.class, args);
	}

}
