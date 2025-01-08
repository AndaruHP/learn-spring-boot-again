package com.tutorial.learn.hateoas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabse(EmployeeRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
                log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
            } else {
                log.info("Data sudah ada di database, tidak melakukan preload.");
            }
        };
    }
}