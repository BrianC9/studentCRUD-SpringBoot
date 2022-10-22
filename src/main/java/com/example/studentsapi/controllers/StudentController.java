package com.example.studentsapi.controllers;

import com.example.studentsapi.models.Student;
import com.example.studentsapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    public StudentController() {
    }

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // findAll
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return studentService.getAll();
    }

    // findeOneById
    @GetMapping("{id}")
    public ResponseEntity<Student> getOneById(@PathVariable Long id) {
        return studentService.getOneById(id);
    }

    // createStudent
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // updateStudent
    @PutMapping(path = "{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        return studentService.updateStudent(id, name, email);
    }

    // deleteOneById
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }


}
