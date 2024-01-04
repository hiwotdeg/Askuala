package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.model.PhoneNumber;
import et.com.gebeya.academicservice.service.PhoneNumberService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path="asquala/phoneNumber")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Phone Number", description = "The Phone Number API. Contains the phone number.")
public class PhoneNumberController {
    PhoneNumberService phoneNumberService;
    @PostMapping("/{addPhoneNumber}")
    public ResponseEntity<String> addAddress(@RequestBody PhoneNumber phoneNumber){

        return phoneNumberService.createPhoneNumber(phoneNumber);
    }

    @GetMapping("/getPhoneNumber/{id}")
    public ResponseEntity<String> getPhoneNumber(@ModelAttribute Long id){

        return phoneNumberService.getPhoneNumberById(id);
    }

    @PutMapping("/updatePhoneNumber/{id}")
    public ResponseEntity<String> updateAddress( @ModelAttribute Long id , @RequestBody PhoneNumber phoneNumber){
        return phoneNumberService.updatePhoneNumber(id,phoneNumber);
    }
}
