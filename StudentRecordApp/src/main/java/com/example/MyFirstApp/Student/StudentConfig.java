package com.example.MyFirstApp.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student vishal = new Student(
                    "Vishal",
                    "vishal854@gmail.com",
                    LocalDate.of(1987, 05, 17)
            );

            Student neha = new Student(
                    "Neha",
                    "neha854@gmail.com",
                    LocalDate.of(1991, Month.JANUARY, 17)
            );

            studentRepository.saveAll(
                    List.of(vishal, neha)
            );
        };
    }
}
