package com.example.studentsapi.services;

import com.example.studentsapi.exception.EmailAlreadyTakenException;
import com.example.studentsapi.models.Student;
import com.example.studentsapi.repository.StudentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentServiceUnderTest;


    @Test
    void ItShouldGetAllTheStudents() {
        Student student1 = new Student("Maika", LocalDate.of(2001, 03, 21), "maika@gmail.com");
        Student student2 = new Student("Paula", LocalDate.of(2001, 03, 21), "paula@gmail.com");
        Student student3 = new Student("Rami", LocalDate.of(2001, 03, 21), "rami@gmail.com");
        List<Student> studentsList = List.of(student1, student2, student3);
        when(studentRepository.findAll()).thenReturn(studentsList);
        //when
        assertThat(studentServiceUnderTest.getAll()).isNotNull();

        //then
        verify(studentRepository, times(1)).findAll();

    }

    @Test
    void createStudent() {
        //given

        Student student1 = new Student("Maika", LocalDate.of(2001, 03, 21), "maika@gmail.com");

        //when
        studentServiceUnderTest.createStudent(student1);
        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student1);


    }

    @Test
    void willthrowEmailExceptionWhenEmailIsTakenAndSaveNeverExecutes() {

        //given

        Student student1 = new Student("Maika", LocalDate.of(2001, 03, 21), "maika@gmail.com");

        given(studentRepository.findStudentByEmail(student1.getEmail()))
                .willReturn(true);
        //then
        assertThatThrownBy(() -> studentServiceUnderTest.createStudent(student1))
                .hasMessageContaining("Email already in use: " + student1.getEmail())
                .isInstanceOf(EmailAlreadyTakenException.class);

        verify(studentRepository, never()).save(any());

    }

    @Test
    @Disabled
    void deleteStudentById() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}