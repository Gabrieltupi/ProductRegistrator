package br.com.school.history.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("br.com.school.history")
@EnableJpaRepositories(basePackages = "br.com.school.history.domain.history")
public class Application {

    public static void main(String[] args) {
        var app = new SpringApplication(Application.class);
        app.run(args);
    }
}