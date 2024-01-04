package et.com.gebeya.academicservice.dto;

import et.com.gebeya.academicservice.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentDto {
    private String name;
    private String studentId;
    private String phoneNumber;
    private String email;
    private BigDecimal price;

    public CreateStudentDto(Student student) {
        this.name = student.getFirstName();
        this.studentId = student.getStudentId();
        this.phoneNumber = student.getPhoneNumber();
        this.email = student.getEmail();
    }
}
