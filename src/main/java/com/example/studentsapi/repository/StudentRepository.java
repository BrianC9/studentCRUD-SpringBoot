package com.example.studentsapi.repository;

import com.example.studentsapi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    // Select * from student WHERE email = 'emailArg'
    /*
     *
     * */
    @Query("SELECT CASE WHEN COUNT(S) > 0 THEN TRUE ELSE FALSE END FROM Student s WHERE s.email = ?1")
    boolean findStudentByEmail(String email);
}
