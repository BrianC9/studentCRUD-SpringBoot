package com.example.studentsapi.config;

import com.example.studentsapi.models.Student;
import com.example.studentsapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student bryan= new Student(
                    "Bryan",

                    LocalDate.of(1998,04,23),
                    "bryan@email.com"
            );
            Student raquel = new Student(
                    "Raquel",

                    LocalDate.of(1999,02,01),
                    "raquel@email.com"
            );
            repository.saveAll(
                    List.of(bryan, raquel)
            );

        };
    }
}
