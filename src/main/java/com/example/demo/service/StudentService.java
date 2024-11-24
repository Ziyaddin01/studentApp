package com.example.demo.service;

import com.example.demo.model.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.request.StudentRequest;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Data
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;
    private static Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Page<Student> getStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    public StudentDTO findStudentById(Long id) {
        logger.info("Find student by id: " + id);
        Student student = studentRepository.findById(id).orElseThrow(() -> new
                StudentNotFoundException("Student not found with ID: " + id));
        return studentMapper.toDto(student);
    }
    public StudentDTO createStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void updateStudent(StudentRequest studentRequest, Student student) {
        studentMapper.updateEntity(studentRequest, student);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String firstName,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        if (firstName != null && !firstName.isEmpty() && !Objects.equals(student.getFirstName(), firstName)) {
            student.setFirstName(firstName);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setFirstName(email);
        }
    }
}