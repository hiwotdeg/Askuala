package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubjectRepository extends JpaRepository<Subject,Long> , JpaSpecificationExecutor<Subject> {
}
