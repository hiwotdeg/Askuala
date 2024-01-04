package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.model.GradeSection;
import et.com.gebeya.academicservice.repository.GradeSectionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeSectionService {
    GradeSectionRepository gradeSectionRepository;

    public ResponseEntity<String> addGradeSection(GradeSection gradeSection) {
        Optional<GradeSection> gradeSectionExist = gradeSectionRepository.findById(gradeSection.getId());
        if(gradeSectionExist.isPresent()){
            return ResponseEntity.badRequest().body("grade section is already registered");
        }
        GradeSection gradeSection1 = gradeSectionExist.get();
        gradeSectionRepository.save(gradeSection1);
        return ResponseEntity.ok("grade section with id "+gradeSection1.getId()+ " is registered successfully");
    }

    public ResponseEntity<String> getGradeSection(Long gradeSectionId) {
        Optional<GradeSection> gradeSectionExist = gradeSectionRepository.findById(gradeSectionId);
        if(gradeSectionExist.isPresent()){
            GradeSection gradeSection = gradeSectionExist.get();
            return  ResponseEntity.ok("grade section details "+gradeSection);
        }
        return ResponseEntity.badRequest().body("grade section with id "+gradeSectionId+" is not found");
    }

    public Iterable<GradeSection> getAllGradeSection() {
        return gradeSectionRepository.findAll();
    }

    public ResponseEntity<String> updateGradeSection(Long gradeSectionId, GradeSection gradeSection) {
        Optional<GradeSection> gradeSectionExist = gradeSectionRepository.findById(gradeSectionId);
        if(gradeSectionExist.isPresent()){
            GradeSection updateGradeSection = gradeSectionExist.get();
            updateGradeSection.setSection(gradeSection.getSection());
            updateGradeSection.setGrade(gradeSection.getGrade());
            updateGradeSection.setHomeRoomTeacherId(gradeSection.getHomeRoomTeacherId());
            updateGradeSection.setSubjects(gradeSection.getSubjects());
            updateGradeSection.setStudents(gradeSection.getStudents());
            gradeSectionRepository.save(updateGradeSection);
            return ResponseEntity.ok("grade section with id "+gradeSection+" is updated successfully");
        }
        return ResponseEntity.badRequest().body("grade section with id "+gradeSection+" is not found");
    }

    public ResponseEntity<String> deleteGradeSection(Long gradeSectionId) {
        Optional<GradeSection> gradeSectionExist = gradeSectionRepository.findById(gradeSectionId);
        if(gradeSectionExist.isPresent()){
            GradeSection gradeSection = gradeSectionExist.get();
            gradeSectionRepository.deleteById(gradeSectionId);
           return  ResponseEntity.ok("grade section with id "+gradeSection+" is deleted");
        }
        return ResponseEntity.badRequest().body("grade section with id "+gradeSectionId+" is not found");
    }
}
