package az.student.app;

import com.example.demo.model.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldFindStudentById() {
        Student student = new Student("Valeh", "Azimov", "valeh.azimov2001@mail.ru", 23);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentDTO found = studentService.findStudentById(1L);

        Assertions.assertEquals("Valeh", "Azimov", found.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFound() {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.findStudentById(1L));
    }

}
