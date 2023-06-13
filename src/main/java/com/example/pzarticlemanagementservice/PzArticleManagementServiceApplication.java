package com.example.pzarticlemanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableFeignClients
@EnableJms
public class PzArticleManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PzArticleManagementServiceApplication.class, args);
    }

}
