package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.model.Attendance;
import et.com.gebeya.academicservice.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;
    public ResponseEntity<String> addAttendance(Attendance attendance) {
        Optional<Attendance> attendanceExist = attendanceRepository.findById(attendance.getId());
        if(attendanceExist.isPresent()){
            return  ResponseEntity.badRequest().body("Attendance is already registered");
        }
        attendanceRepository.save(attendance);
        return ResponseEntity.ok("attendance is registered successfully");
    }

    public ResponseEntity<String> getAttendanceById(Long attendanceId) {
        Optional<Attendance> attendanceExist = attendanceRepository.findById(attendanceId);
        if(attendanceExist.isPresent()){
            Attendance attendance = attendanceExist.get();
            return ResponseEntity.ok("details" +attendance);
        }
        return ResponseEntity.badRequest().body("attendance with id "+attendanceId+ " is not found");

    }

    public Iterable<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
