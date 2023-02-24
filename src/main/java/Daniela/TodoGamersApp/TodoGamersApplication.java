package Daniela.TodoGamersApp;

import Daniela.TodoGamersApp.Controller.AdminController;
import Daniela.TodoGamersApp.Controller.AssetsController;
import Daniela.TodoGamersApp.Controller.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
@ComponentScan(basePackageClasses= MainController.class)
@ComponentScan(basePackageClasses= AdminController.class)
@ComponentScan(basePackageClasses= AssetsController.class)
public class TodoGamersApplication {

    public static void main(String[] args) {

        SpringApplication.run(TodoGamersApplication.class, args);
    }

    @Bean
    public SpringDataDialect springDataDialect() {

        return new SpringDataDialect();
    }

}