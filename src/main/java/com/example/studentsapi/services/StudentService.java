package com.example.studentsapi.services;

import com.example.studentsapi.exception.EmailAlreadyTakenException;
import com.example.studentsapi.models.Student;
import com.example.studentsapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentRepository.findAll());
    }


    public ResponseEntity<Student> getOneById(Long id) {
        if (!studentRepository.existsById(id)) return ResponseEntity.notFound().build();
        Optional<Student> result = studentRepository.findById(id);
        return ResponseEntity.ok(result.get());
    }

    public ResponseEntity<Student> createStudent(Student student) {

        if (studentRepository.findStudentByEmail(student.getEmail())) {
            throw new EmailAlreadyTakenException("Email already in use: " + student.getEmail());
        }
        Student result = studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Student> deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) return ResponseEntity.notFound().build();
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Student> updateStudent(Long id, String name, String email) {
        if (!studentRepository.existsById(id)) return ResponseEntity.notFound().build();
        Student resultStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exists"));
        if (name != null && name.length() > 0 && !name.equalsIgnoreCase(resultStudent.getName())) {
            resultStudent.setName(name);
            if (email != null && email.length() > 0 && !email.equalsIgnoreCase(resultStudent.getEmail())) {

                if (studentRepository.findStudentByEmail(email)) {
                    throw new EmailAlreadyTakenException("Email already in use: " + email);
                }
                resultStudent.setEmail(email);
            }

        }
        studentRepository.save(resultStudent);
        return ResponseEntity.ok(resultStudent);
    }
}
