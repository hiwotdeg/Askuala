package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.model.Teacher;
import et.com.gebeya.academicservice.service.TeacherService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/asquala/teacher")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Teacher", description = "The Teacher API. Contains the Teacher information.")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/addTeacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        return teacherService.addTeacher(teacher);
    }

    @GetMapping("/getTeacher/{teacherId}")
    public ResponseEntity<String> getTeacher(@ModelAttribute Long teacherId){
        return teacherService.getTeacherById(teacherId);
    }
    @GetMapping("/getTeacher")
    public Iterable<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @DeleteMapping("/deleteTeacher/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@ModelAttribute Long teacherId){
        return teacherService.deleteTeacher(teacherId);
    }
    @PutMapping("/updateTeacher/{teacherId}")
    public ResponseEntity<String> updateTeacher( @ModelAttribute Long teacherId , @RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacherId,teacher);
    }
}
