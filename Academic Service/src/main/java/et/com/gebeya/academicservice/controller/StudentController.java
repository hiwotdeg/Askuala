package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.dto.CreateStudentDto;
import et.com.gebeya.academicservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academic/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<CreateStudentDto> createStudent(@RequestBody CreateStudentDto createStudentDto) {
        return studentService.createStudent(createStudentDto);
    }
}
