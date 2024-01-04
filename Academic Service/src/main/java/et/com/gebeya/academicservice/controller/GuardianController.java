package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.model.Guardian;
import et.com.gebeya.academicservice.service.GuardianService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/asquala/guardian")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Guardian", description = "The Guardian API. Contains the Guardian information.")
public class GuardianController {
    @Autowired
    GuardianService guardianService;
    @PostMapping("/{addGuardian}")
    public ResponseEntity<String> addGuardian(@RequestBody Guardian guardian){

        return guardianService.addGuardian(guardian);
    }

    @GetMapping("/getGuardian/{guardianId}")
    public ResponseEntity<String> getGuardian(@ModelAttribute Long guardianId){

        return guardianService.getGuardian(guardianId);
    }

    @PutMapping("/updateGuardian/{guardianId}")
    public ResponseEntity<String> updateGuardian( @ModelAttribute Long guardianId , @RequestBody Guardian guardian){
        return guardianService.updateGuardian(guardianId,guardian);
    }
//    @GetMapping("/getGuardian")
//    public Iterable<Guardian> getAllGuardian(){
//        return null;
//    }
//
    @DeleteMapping("/deleteGuardian/{guardianId}")
    public ResponseEntity<String> deleteGuardian(@ModelAttribute Long guardianId){
        return guardianService.deleteGuardian(guardianId);
    }

}
