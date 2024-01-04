package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.model.Subject;
import et.com.gebeya.academicservice.service.SubjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/asquala/subject")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Subject", description = "The Subject API. Contains the Courses information.")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @PostMapping("/{addSubject}")
    public ResponseEntity<String> addSubject(@RequestBody Subject subject){

        return subjectService.addSubject(subject);
    }

    @GetMapping("/getSubject/{id}")
    public ResponseEntity<String> getSubject(@ModelAttribute Long id){

        return subjectService.getSubjectById(id);
    }
    @GetMapping("/getSubject")
    public Iterable<Subject> getAllSubject(){

        return subjectService.getAllSubject();
    }

    @DeleteMapping("/deleteSubject/{id}")
    public ResponseEntity<String> deleteSubject(@ModelAttribute Long id){

        return subjectService.deleteSubject(id);
    }
    @PutMapping("/updateSubject/{id}")
    public ResponseEntity<String> updateSubject( @ModelAttribute Long id , @RequestBody Subject subject){
        return subjectService.updateSubject(id,subject);
    }
}
