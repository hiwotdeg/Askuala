package et.com.gebeya.academicservice.model;

import et.com.gebeya.academicservice.model.Enumeration.AttendanceRemark;
import et.com.gebeya.academicservice.model.Enumeration.AttendanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance extends BaseModel {

    @Column(name = "student_id" , nullable = false)
    private Long studentId;
    @Column(name = "teacher_id" , nullable = false)
    private Long teacherId;
    private Date attendanceDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
    @Enumerated(EnumType.STRING)
    private AttendanceRemark remark;

}
