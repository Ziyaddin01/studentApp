package com.example.demo.controller;

import com.example.demo.model.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.model.request.StudentRequest;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{email}")
    public List<Student> getStudents(@PathVariable String email) {
        return studentService.getStudents();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
    @PutMapping("update_students")
    public void updateStudent(
                              @PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, firstName ,email);
    }
    @PostMapping("create_student")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        StudentDTO createdStudent = studentService.createStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping
    public ResponseEntity<Page<Student>>getStudents(@RequestParam(defaultValue = "0")int page,
                                                    @RequestParam(defaultValue = "10")int size) {
        Page<Student> students = studentService.getStudents(page, size);
        return ResponseEntity.ok(students);
    }


}
