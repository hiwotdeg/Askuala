package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.dto.PaymentInformation;
import et.com.gebeya.academicservice.dto.RequestDto;
import et.com.gebeya.academicservice.dto.ResponseDto;
import et.com.gebeya.academicservice.dto.StudentRequestDto;
import et.com.gebeya.academicservice.model.Student;
import et.com.gebeya.academicservice.model.Users;
import et.com.gebeya.academicservice.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
@Builder
@Service
@Configuration
@Slf4j
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private SpecificationService<Student> specificationService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WebClient.Builder webClientBuilder;

    private KafkaTemplate<String , StudentRequestDto> studentRequestDtoKafkaTemplate;

    public ResponseEntity<String> addStudent(Student studentRequest){
        Optional<Student> checkStudentExistence = studentRepository.findById(studentRequest.getId());
        if(checkStudentExistence.isPresent()){
           ResponseEntity.badRequest().body("student with id " + studentRequest.getId() +"is already registered");
        }
        studentRepository.save(studentRequest);
        Users users = Users.builder()
                .name(studentRequest.getFirstName())
                .isActive(studentRequest.getIsActive())
                .role(studentRequest.getRole())
                .userName(studentRequest.getFirstName())
                .password(passwordEncoder.encode(studentRequest.getPassword()))
                .build();
        usersService.createUser(users);

        StudentRequestDto studentRequestDtos2 = StudentRequestDto.builder()
                .firstName(studentRequest.getFirstName())
                .middleName(studentRequest.getMiddleName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .price(studentRequest.getPrice())
                .build();
        studentRequestDtoKafkaTemplate.send("STUDENT_TOPIC",studentRequestDtos2);
//
        return  ResponseEntity.ok("student registered successfully");
    }

    public List<Student> getSpecificStudentS(RequestDto requestDto){
        Specification<Student> specificStudents = specificationService.
                getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        return studentRepository.findAll(specificStudents);

    }

    public ResponseEntity<String> getStudentById(Long id) {
        Optional<Student> studentExist = studentRepository.findById(id);
        if(studentExist.isPresent()){
            Student student = studentExist.get();

            ResponseDto responseDto = ResponseDto.builder()
                            .firstName(student.getFirstName())
                            .middleName(student.getMiddleName())
                            .lastName(student.getLastName())
                            .is_Active(student.getIsActive()).build();

           return ResponseEntity.ok("student details : "+responseDto);
        }
       return ResponseEntity.badRequest().body("student with id "+id +" is not found");
    }

    @Transactional
    public ResponseEntity<String> updateStudent(Long studentId, Student updateStudent) {
        Optional<Student> studentExist = studentRepository.findById(studentId);
        if(studentExist.isPresent()){
           Student student = studentExist.get();
           student.setAddress(updateStudent.getAddress());
           student.setGradeSections(updateStudent.getGradeSections());
           studentRepository.save(student);
           return ResponseEntity.ok("student with the id "+studentId+" file is updated");
        }
        return ResponseEntity.badRequest().body("student with id "+studentId+" is not found");
    }
    @Transactional
    public ResponseEntity<String> deleteStudent(Long studentId) {
        Optional<Student> studentExist = studentRepository.findById(studentId);
        if(studentExist.isPresent()){
            studentRepository.deleteById(studentId);
            return ResponseEntity.ok("student with id "+studentId+" is successfully deleted");
        }
        return ResponseEntity.badRequest().body("student with the id "+studentId+" is not found");
    }

    public Iterable<Student> getAllStudent() {
       return studentRepository.findAll();
    }
    private void createStudentPaymentInformation(Student student) {
        PaymentInformation paymentInformation = PaymentInformation.builder()
                .studentId(student.getStudentId())
                .price(student.getPrice())
                .build();

        String response = webClientBuilder.build().post()
                .uri("http://Payment-Service/api/payment/student")
                .bodyValue(paymentInformation)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Response from payment micro service==> {}", response);
    }


}

