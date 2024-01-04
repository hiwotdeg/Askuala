package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.dto.ResponseDto;
import et.com.gebeya.academicservice.model.Teacher;
import et.com.gebeya.academicservice.model.Users;
import et.com.gebeya.academicservice.model.Enumeration.Role;
import et.com.gebeya.academicservice.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.Optional;
@Service
@Builder
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ResponseEntity<String> addTeacher(Teacher teacher) {
        Optional<Teacher> teacherExist = teacherRepository.findById(teacher.getId());
        if(teacherExist.isPresent()){
           return ResponseEntity.badRequest().body("the teacher with id "+teacher.getId()+" is already registered");
        }
        Teacher newTeacher = teacherExist.get();
        teacherRepository.save(newTeacher);
        Users users = Users.builder()
                .name(teacher.getFirstName())
                .isActive(teacher.getIsActive())
                .role(teacher.getRole())
                .userName(teacher.getFirstName())
                .password(passwordEncoder.encode(teacher.getPassword()))
                .build();
        usersService.createUser(users);
        return ResponseEntity.ok("new teacher with id "+teacher.getId()+" is registered");
    }

    public ResponseEntity<String> getTeacherById(Long teacherId) {
        Optional<Teacher> teacherExist = teacherRepository.findById(teacherId);
        if(teacherExist.isPresent()){
            Teacher teacher = teacherExist.get();
            ResponseDto responseDto = ResponseDto.builder()
                    .firstName(teacher.getFirstName())
                    .middleName(teacher.getMiddleName())
                    .lastName(teacher.getLastName())
                    .is_Active((teacher.getIsActive())).build();

           return ResponseEntity.ok("teacher details : "+responseDto );
        }
        return ResponseEntity.badRequest().body("the teacher with id "+teacherId+" is not found");
    }

    public Iterable<Teacher> getAllTeacher() {
        return  teacherRepository.findAll();
    }
    @Transactional
    public ResponseEntity<String> deleteTeacher(Long teacherId) {
        Optional<Teacher> teacherExist = teacherRepository.findById(teacherId);
        if(teacherExist.isPresent()){
            Teacher teacher = teacherExist.get();
            teacherRepository.deleteById(teacherId);
            return ResponseEntity.ok("teacher with id "+teacherId+" is deleted successfully");
        }
        return ResponseEntity.badRequest().body("teacher with id "+teacherId+" is not found");
    }
    @Transactional
    public ResponseEntity<String> updateTeacher(Long teacherId, Teacher teacher) {
        Optional<Teacher> teacherExist = teacherRepository.findById(teacherId);
        if(teacherExist.isPresent()){
            Teacher updateTeacher = teacherExist.get();
            updateTeacher.setQualification(teacher.getQualification());
            updateTeacher.setSubjects(teacher.getSubjects());
            teacherRepository.save(updateTeacher);
            ResponseEntity.ok("the teacher with id "+teacherId+" file is updated");
        }
        return ResponseEntity.badRequest().body("the teacher with id "+teacherId+ " is not found");
    }
}
