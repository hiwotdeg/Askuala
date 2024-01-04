package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GuardianRepository extends JpaRepository<Guardian,Long>, JpaSpecificationExecutor<Guardian> {
}
