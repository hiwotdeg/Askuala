package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.model.Guardian;
import et.com.gebeya.academicservice.repository.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuardianService {
    @Autowired
    GuardianRepository guardianRepository;

    public ResponseEntity<String> addGuardian(Guardian guardian) {
        Optional<Guardian> guardianExist = guardianRepository.findById(guardian.getId());
        if(guardianExist.isPresent()){
           return ResponseEntity.badRequest().body("the guardian with id "+guardian.getId()+" is already registered");
        }
        Guardian newGuardian = guardianExist.get();
        guardianRepository.save(newGuardian);
        return ResponseEntity.ok("the guardian is successfully registered with id "+guardian.getId());
    }

    public ResponseEntity<String> getGuardian(Long guardianId) {
        Optional<Guardian> guardianExist = guardianRepository.findById(guardianId);
        if(guardianExist.isPresent()){
            Guardian guardian = guardianExist.get();
            return ResponseEntity.ok("details" +guardian);
        }
        return ResponseEntity.badRequest().body("guardian with id "+guardianId+ " is not found");
    }

    public ResponseEntity<String> updateGuardian(Long guardianId, Guardian guardian) {
        Optional<Guardian> guardianExist = guardianRepository.findById(guardianId);
        if(guardianExist.isPresent()){
            Guardian updateGuardian = guardianExist.get();
            updateGuardian.setAddress(updateGuardian.getAddress());
            return  ResponseEntity.ok("the guardian with id "+guardianId+ " is successfully updated his/her profile");
        }
        return ResponseEntity.badRequest().body("the guardian with id "+guardianId+" is not found");

    }

    public ResponseEntity<String> deleteGuardian(Long guardianId) {
        Optional<Guardian> guardianExist = guardianRepository.findById(guardianId);
        if(guardianExist.isPresent()){
            Guardian guardian = guardianExist.get();
            guardianRepository.deleteById(guardianId);
            return ResponseEntity.ok("the guardian with id "+guardianId+" is successfully deleted");
        }
        return ResponseEntity.badRequest().body("the guardian with id "+guardianId+" is not found");
    }
}
