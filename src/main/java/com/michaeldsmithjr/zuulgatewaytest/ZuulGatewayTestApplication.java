package com.michaeldsmithjr.zuulgatewaytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulGatewayTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayTestApplication.class, args);
	}
}
