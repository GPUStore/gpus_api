package ru.mephi.gpus_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GpusApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpusApiApplication.class, args);
    }

}
