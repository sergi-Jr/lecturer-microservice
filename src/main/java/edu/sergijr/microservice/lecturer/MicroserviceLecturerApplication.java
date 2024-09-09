package edu.sergijr.microservice.lecturer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MicroserviceLecturerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceLecturerApplication.class, args);
    }
}
