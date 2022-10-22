package com.example.studentsapi.repository;

import com.example.studentsapi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    // Select * from student WHERE email = 'emailArg'
    @Query("Select s from Student  s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
