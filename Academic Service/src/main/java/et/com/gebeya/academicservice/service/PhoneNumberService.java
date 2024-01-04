package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.model.PhoneNumber;
import et.com.gebeya.academicservice.repository.PhoneNumberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PhoneNumberService {
    PhoneNumberRepository phoneNumberRepository;
    public ResponseEntity<String> createPhoneNumber(PhoneNumber phoneNumber) {
        Optional<PhoneNumber> phoneNumberExist = phoneNumberRepository.findById(phoneNumber.getId());
        if(phoneNumberExist.isPresent()){
            return ResponseEntity.badRequest().body("address with id "+phoneNumber.getId()+" is already registered");
        }
        phoneNumberRepository.save(phoneNumber);
        return ResponseEntity.ok("Phone Number is successfully registered");
    }

    public ResponseEntity<String> getPhoneNumberById(Long id) {
        Optional<PhoneNumber> phoneNumberExist = phoneNumberRepository.findById(id);
        if(phoneNumberExist.isPresent()){
            PhoneNumber phoneNumber = phoneNumberExist.get();
            return ResponseEntity.ok("Address details of id "+id+" "+ phoneNumber);
        }
        return ResponseEntity.ok("Address with id " +id+ " is not found");
    }

    public ResponseEntity<String> updatePhoneNumber(Long id, PhoneNumber phoneNumber) {

        Optional<PhoneNumber> phoneNumberExist = phoneNumberRepository.findById(phoneNumber.getId());
        if(phoneNumberExist.isPresent()){
            PhoneNumber updatePhoneNumber = phoneNumberExist.get();
            updatePhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());
            updatePhoneNumber.setAddressId(phoneNumber.getAddressId());
            return ResponseEntity.ok("phoneNumber with id "+id+" is updated successfully");
        }

        return ResponseEntity.badRequest().body("phoneNumber with id "+id+ " is not found");

    }
}
