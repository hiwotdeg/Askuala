package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.GradeSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GradeSectionRepository extends JpaRepository<GradeSection,Long> , JpaSpecificationExecutor<GradeSection> {
}
