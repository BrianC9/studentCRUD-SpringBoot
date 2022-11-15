package com.example.studentsapi.repository;

import com.example.studentsapi.models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repoTest;

    @BeforeAll
    void loadSomeStudents() {
        Student student1 = new Student("Maika", LocalDate.of(2001, 03, 21), "maika@gmail.com");
        Student student2 = new Student("Paula", LocalDate.of(2001, 03, 21), "paula@gmail.com");
        Student student3 = new Student("Rami", LocalDate.of(2001, 03, 21), "rami@gmail.com");
        List<Student> studentsList = List.of(student1, student2, student3);
        repoTest.saveAll(studentsList);
    }

    @AfterEach
    void cleanDB() {
        repoTest.deleteAll();
    }

    @Test
    void itShouldCountTheStudents() {
        int nExpected = 3;
        assertThat(nExpected).isEqualTo(repoTest.count());
    }

    @Test
    void itShouldCheckIfStudentExistsByEmail() {
        // Given

        Student student = new Student("Kevin", LocalDate.of(2001, 03, 21), "kevin@gmail.com");
        repoTest.save(student);
        // When
        boolean expectedStudent = repoTest.findStudentByEmail(student.getEmail()).isPresent() ? true : false;
        // Then
        assertThat(expectedStudent).isTrue();
    }


    @Test
    void itShouldCheckIfStudentDoesNotExistsByEmail() {
        // Given
        String email = "kevin@gmail.com";
        // When
        boolean expectedStudent = repoTest.findStudentByEmail(email).isPresent() ? true : false;
        // Then
        assertThat(expectedStudent).isFalse();
    }
}