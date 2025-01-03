package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {
}
