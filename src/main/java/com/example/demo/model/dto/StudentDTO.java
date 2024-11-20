package com.example.demo.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    @NotNull(message = "FirstName cannot be null")
    private String firstName;
    @NotNull(message = "LastName cannot be null")
    private String lastName;
    private String email;
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 45, message = "Age must be less than or equal to 45")
    private Integer age;
}
