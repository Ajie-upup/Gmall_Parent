package com.ajie.gmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ajie
 * @date 2023/3/6
 * @description:
 */
@SpringBootApplication
@ComponentScan("com.ajie.gmall")
@EnableDiscoveryClient
public class ManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class);
    }
}
