package et.com.gebeya.paymentservice.controller;

import et.com.gebeya.paymentservice.model.StudentPaymentInformation;
import et.com.gebeya.paymentservice.service.StudentPaymentInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment/student")
@RequiredArgsConstructor
public class StudentPaymentInformationController {

    private final StudentPaymentInformationService paymentInformationService;

    @PostMapping
    public ResponseEntity<String> createStudentPaymentInformation(@RequestBody StudentPaymentInformation studentPaymentInformation) {
        return paymentInformationService.createPaymentInformation(studentPaymentInformation);

    }
}
