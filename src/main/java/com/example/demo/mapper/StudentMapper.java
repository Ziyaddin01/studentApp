package com.example.demo.mapper;

import com.example.demo.model.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.model.request.StudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDto(Student student);
    Student toEntity(StudentRequest studentRequest);


    @Mapping(target = "id", ignore = true)
    void updateEntity(StudentRequest studentRequest, @MappingTarget Student student);


}
