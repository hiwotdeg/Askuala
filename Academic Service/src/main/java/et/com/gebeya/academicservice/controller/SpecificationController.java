package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.dto.PageDto;
import et.com.gebeya.academicservice.dto.RequestDto;
import et.com.gebeya.academicservice.model.Student;
import et.com.gebeya.academicservice.repository.StudentRepository;
import et.com.gebeya.academicservice.service.SpecificationService;
import et.com.gebeya.academicservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/asquala/specification")
public class SpecificationController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SpecificationService<Student> specificationService;
    @Autowired
    StudentService studentService;
    @PostMapping("/specification")
    public List<Student> getSpecificStudent(@RequestBody RequestDto requestDto){
//        Specification<Student> specificStudents =  specificationService.
//                getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
//        return studentRepository.findAll(specificStudents);

        return studentService.getSpecificStudentS(requestDto);
    }
    @PostMapping("/specification/pagination")
    public Page<Student> getPageableSpecificStudent(@RequestBody RequestDto requestDto){
        Specification<Student> specificStudents =  specificationService.
                getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageDto().getPageable(requestDto.getPageRequestDto());
        return studentRepository.findAll(specificStudents,pageable);
    }
}
