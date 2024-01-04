package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.model.Subject;
import et.com.gebeya.academicservice.repository.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SubjectService {
    SubjectRepository subjectRepository;
    public ResponseEntity<String> addSubject(Subject subject) {
        Optional<Subject> subjectExist = subjectRepository.findById(subject.getId());
        if(subjectExist.isPresent()){
            return ResponseEntity.badRequest().body("Subject with id "+subject.getId()+" is already registered");
        }
        subjectRepository.save(subject);
        return ResponseEntity.ok("Subject is successfully Added  ");
    }

    public ResponseEntity<String> getSubjectById(Long id) {
        Optional<Subject> subjectExist = subjectRepository.findById(id);
        if(subjectExist.isPresent()){
            Subject subject = subjectExist.get();
            return ResponseEntity.ok("Subject details of id "+id+" "+ subject);
        }
        return ResponseEntity.ok("Subject with id " +id+ " is not found");
    }

    public Iterable<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    public ResponseEntity<String> deleteSubject(Long id) {
        Optional<Subject> subjectExist = subjectRepository.findById(id);
        if(subjectExist.isPresent()){
            subjectRepository.deleteById(id);
            return ResponseEntity.ok("subject with id "+id+" is successfully deleted");
        }
        return ResponseEntity.badRequest().body("Subject with the id "+id+" is not found");
    }

    public ResponseEntity<String> updateSubject(Long id , Subject subject) {
        Optional<Subject> subjectExist = subjectRepository.findById(subject.getId());
        if(subjectExist.isPresent()){
            Subject updateSubject = subjectExist.get();
            updateSubject.setGradeSections(subject.getGradeSections());
            updateSubject.setCourseName(subject.getCourseName());
            updateSubject.setTeachers(subject.getTeachers());
            subjectRepository.save(updateSubject);
            return ResponseEntity.ok("Subject with id "+id+" is updated successfully");
        }

        return ResponseEntity.badRequest().body("Subject with id "+id+ " is not found");

    }
}
