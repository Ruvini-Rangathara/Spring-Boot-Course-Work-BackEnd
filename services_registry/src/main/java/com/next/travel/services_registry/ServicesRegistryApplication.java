package com.next.travel.services_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServicesRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesRegistryApplication.class, args);
    }

}
