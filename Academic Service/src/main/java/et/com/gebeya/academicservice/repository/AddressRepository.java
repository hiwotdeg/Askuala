package et.com.gebeya.academicservice.repository;

import et.com.gebeya.academicservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<Address,Long>, JpaSpecificationExecutor<Address> {
}
