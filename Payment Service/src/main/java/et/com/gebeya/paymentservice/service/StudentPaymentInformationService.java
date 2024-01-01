package et.com.gebeya.paymentservice.service;

import et.com.gebeya.paymentservice.model.StudentPaymentInformation;
import et.com.gebeya.paymentservice.repository.StudentPaymentInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentPaymentInformationService {
    private final StudentPaymentInformationRepository paymentInformationRepository;

    public ResponseEntity<String> createPaymentInformation(StudentPaymentInformation studentPaymentInformation) {
        paymentInformationRepository.save(studentPaymentInformation);
        return ResponseEntity.ok(String.format("Payment Information for Student Id: %s created", studentPaymentInformation.getStudentId()));
    }
}
