package et.com.gebeya.paymentservice.repository;

import et.com.gebeya.paymentservice.model.StudentPaymentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPaymentInformationRepository extends JpaRepository<StudentPaymentInformation, Long> {
}
