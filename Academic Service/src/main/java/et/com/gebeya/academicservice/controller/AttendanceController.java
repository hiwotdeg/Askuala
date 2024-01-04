package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.model.Attendance;
import et.com.gebeya.academicservice.service.AttendanceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/asquala/attendance")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Attendance", description = "The Attendance API. Contains the attendance information.")
public class AttendanceController {
    AttendanceService attendanceService;
    @PostMapping("/{addAttendance}")
    public ResponseEntity<String> addAttendance(@RequestBody Attendance attendance){

        return attendanceService.addAttendance(attendance);
    }

    @GetMapping("/getAttendance/{attendanceId}")
    public ResponseEntity<String> getAttendance(@ModelAttribute Long attendanceId){

        return attendanceService.getAttendanceById(attendanceId);
    }

    @GetMapping("/getAttendance")
    public Iterable<Attendance> getAllAttendance(){

        return attendanceService.getAllAttendance();
    }

//    @PutMapping("/updateAttendance/{attendanceId}")
//    public ResponseEntity<String> updateAttendance( @ModelAttribute Long attendanceId , @RequestBody Attendance attendance){
//        return null;
//    }

//    @DeleteMapping("/deleteAttendance/{attendanceId}")
//    public ResponseEntity<String> deleteAttendance(@ModelAttribute Long attendanceId){
//        return null;
//    }


}
