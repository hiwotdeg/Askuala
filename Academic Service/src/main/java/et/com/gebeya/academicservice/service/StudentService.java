package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.dto.CreateStudentDto;
import et.com.gebeya.academicservice.dto.PaymentInformation;
import et.com.gebeya.academicservice.model.Student;
import et.com.gebeya.academicservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final WebClient.Builder webClientBuilder;

    public ResponseEntity<CreateStudentDto> createStudent(CreateStudentDto studentDto) {
        Student student = new Student(studentDto);
        studentRepository.save(student);
        createStudentPaymentInformation(studentDto);
        return ResponseEntity.ok(studentDto);
    }

    private void createStudentPaymentInformation(CreateStudentDto studentDto) {
        PaymentInformation paymentInformation = PaymentInformation.builder()
                .studentId(studentDto.getStudentId())
                .price(studentDto.getPrice())
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
