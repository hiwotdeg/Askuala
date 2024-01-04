package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.model.Address;
import et.com.gebeya.academicservice.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    AddressRepository addressRepository;
    public ResponseEntity<String> createAddress(Address address) {
        Optional<Address> addressExist = addressRepository.findById(address.getId());
        if(addressExist.isPresent()){
            return ResponseEntity.badRequest().body("address with id "+address.getId()+" is already registered");
        }
        addressRepository.save(address);
        return ResponseEntity.ok("Address is successfully registered");
    }

    public ResponseEntity<String> getAddressById(Long addressId) {
        Optional<Address> addressExist = addressRepository.findById(addressId);
        if(addressExist.isPresent()){
            Address address = addressExist.get();
            return ResponseEntity.ok("Address details of id "+addressId+" "+ address);
        }
        return ResponseEntity.ok("Address with id " +addressId+ " is not found");
    }

    public ResponseEntity<String> updateAddress(Long addressId, Address address) {
        Optional<Address> addressExist = addressRepository.findById(address.getId());
        if(addressExist.isPresent()){
            Address updateAddress = addressExist.get();
            updateAddress.setCity(address.getSubCity());
            updateAddress.setCity(address.getCity());
            updateAddress.setEmail(address.getEmail());
            updateAddress.setHouseNumber(address.getHouseNumber());
            updateAddress.setWereda(address.getWereda());
            updateAddress.setPhoneNumbers(address.getPhoneNumbers());
            addressRepository.save(updateAddress);
            return ResponseEntity.ok("Address with id "+addressId+" is updated successfully");
        }

        return ResponseEntity.badRequest().body("address with id "+addressId+ " is not found");

    }
}
