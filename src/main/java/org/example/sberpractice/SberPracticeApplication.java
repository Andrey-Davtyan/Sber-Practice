package org.example.sberpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SberPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SberPracticeApplication.class, args);
        System.out.println("После запуска проекта перейдите по ссылке в браузере http://localhost:8080/clients");
    }

}
