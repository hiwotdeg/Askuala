package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.dto.CreateStudentDto;
import et.com.gebeya.academicservice.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/academic/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

/*@PostMapping
    @CircuitBreaker(name = "payment-service", fallbackMethod = "fallBackMethod")
    public ResponseEntity<CreateStudentDto> createStudent(@RequestBody CreateStudentDto createStudentDto) {
        return studentService.createStudent(createStudentDto);
    }

    public ResponseEntity<CreateStudentDto> fallBackMethod(CreateStudentDto createStudentDto, RuntimeException runtimeException) {
        return ResponseEntity.badRequest().body(createStudentDto);
    }*/

    @PostMapping
    @CircuitBreaker(name = "payment-service", fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = "payment-service")
    @Retry(name = "payment-service")
    public CompletableFuture<ResponseEntity<CreateStudentDto>> createStudent(@RequestBody CreateStudentDto createStudentDto) {
        return CompletableFuture.supplyAsync(() -> studentService.createStudent(createStudentDto));
    }

    public CompletableFuture<ResponseEntity<CreateStudentDto>> fallBackMethod(CreateStudentDto createStudentDto, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.badRequest().body(createStudentDto));
    }
}
