package com.example.demo.service.impl;

import com.example.demo.model.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.request.StudentRequest;
import org.mapstruct.Mapper;

@Mapper
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        student.setId(student.getId());
        student.setFirstName(student.getFirstName());
        student.setLastName(student.getLastName());
        student.setEmail(student.getEmail());
        student.setAge(student.getAge());
        return studentDTO;
    }

        @Override
        public Student toEntity(StudentRequest studentRequest){
            if (studentRequest == null) {
                return null;
        }
            Student student = new Student();
            student.setFirstName(studentRequest.getFirstName());
            student.setLastName(studentRequest.getLastName());
            student.setEmail(studentRequest.getEmail());
            student.setAge(studentRequest.getAge());
            return student;
    }

    @Override
    public void updateEntity(StudentRequest studentRequest, Student student) {
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());
    }
}