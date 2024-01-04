package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> , JpaSpecificationExecutor<Attendance> {
}
