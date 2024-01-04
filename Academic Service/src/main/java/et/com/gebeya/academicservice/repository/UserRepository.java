package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> , JpaSpecificationExecutor<Users> {
    Optional<Users> findFirstByUserName(String userName);
}
