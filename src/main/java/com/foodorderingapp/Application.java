package com.foodorderingapp;

import com.foodorderingapp.config.YAMLConfig;
import com.foodorderingapp.utils.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private YAMLConfig myConfig;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public StorageProperties storageProperties() {
        return new StorageProperties();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }


    public void run(String... args) throws Exception {
        System.out.println("driver : " + myConfig.getDriver());
        System.out.println("url: " + myConfig.getUrl());
        System.out.println("username: " + myConfig.getUsername());
        System.out.println("password: " + myConfig.getPassword());
        System.out.println("dialect: " + myConfig.getDialect());
    }
}
