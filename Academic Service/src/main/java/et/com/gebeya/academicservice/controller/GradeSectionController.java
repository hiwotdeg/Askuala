package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.model.GradeSection;
import et.com.gebeya.academicservice.service.GradeSectionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/asquala/gradeSection")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "GradeSection", description = "The GradeSection API. Contains the GradeSection information.")
public class GradeSectionController {
    GradeSectionService gradeSectionService;
    @PostMapping("/{addGradeSection}")
    public ResponseEntity<String> addGradeSection(@RequestBody GradeSection gradeSection){

        return gradeSectionService.addGradeSection(gradeSection);
    }

    @GetMapping("/getGradeSection/{gradeSectionId}")
    public ResponseEntity<String> getGradeSection(@ModelAttribute Long gradeSectionId){

        return gradeSectionService.getGradeSection(gradeSectionId);
    }
    @GetMapping("/getGradeSection")
    public Iterable<GradeSection> getAllGradeSection(){

        return gradeSectionService.getAllGradeSection();
    }

    @PutMapping("/updateGradeSection/{gradeSectionId}")
    public ResponseEntity<String> updateGradeSection( @ModelAttribute Long gradeSectionId , @RequestBody GradeSection gradeSection){
        return gradeSectionService.updateGradeSection(gradeSectionId,gradeSection);
    }

    @DeleteMapping("/deleteGradeSection/{gradeSectionId}")
    public ResponseEntity<String> deleteGradeSection(@ModelAttribute Long gradeSectionId){

        return gradeSectionService.deleteGradeSection(gradeSectionId);
    }

}
