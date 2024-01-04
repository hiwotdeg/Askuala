package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherRepository extends JpaRepository<Teacher,Long> , JpaSpecificationExecutor<Teacher> {
}
