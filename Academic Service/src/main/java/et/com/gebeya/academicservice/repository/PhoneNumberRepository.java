package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Long>, JpaSpecificationExecutor<PhoneNumber> {
}
