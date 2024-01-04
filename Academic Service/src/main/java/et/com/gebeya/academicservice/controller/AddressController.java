package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.model.Address;
import et.com.gebeya.academicservice.service.AddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/asquala/address")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Address", description = "The Address API. Contains the address information.")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping("/{addAddress}")
    public ResponseEntity<String> addAddress(@RequestBody Address address) {

        return addressService.createAddress(address);
    }

    @GetMapping("/getAddress/{id}")
    public ResponseEntity<String> getAddress(@ModelAttribute Long id) {

        return addressService.getAddressById(id);
    }

    @PutMapping("/updateAddress/{id}")
    public ResponseEntity<String> updateAddress(@ModelAttribute Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

//    @GetMapping("/getAllAddress")
//    public Iterable<Address> getAllAddress(){
//        return null;
//    }

//    @DeleteMapping("/deleteAddress/{addressId}")
//    public ResponseEntity<String> deleteAddress(@ModelAttribute Long addressId){
//        return null;
//    }

}
