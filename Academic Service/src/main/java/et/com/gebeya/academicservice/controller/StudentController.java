package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.dto.StudentRequestDto;
import et.com.gebeya.academicservice.model.Student;
import et.com.gebeya.academicservice.service.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asquala/student")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Student", description = "The Student API. Contains the Student information.")
public class StudentController {
  @Autowired
  StudentService studentService;
    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<String> getStudentById(@PathVariable Long id){
        return  studentService.getStudentById(id);
    }


    @GetMapping("/getStudent")
    public Iterable<Student> getAllStudent(){

        return studentService.getAllStudent();
    }

    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<String> updateStudent( @ModelAttribute Long studentId , @RequestBody Student student){
        return studentService.updateStudent(studentId,student);
    }
    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<String> deleteStudent(@ModelAttribute Long studentId){

        return studentService.deleteStudent(studentId);
    }



}
