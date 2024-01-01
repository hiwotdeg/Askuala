package et.com.gebeya.academicservice.model;

import et.com.gebeya.academicservice.dto.CreateStudentDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Getter
@Setter
@Table(name = "student")
public class Student extends BaseModel {
    private String name;
    @Column(unique = true, nullable = false)
    private String studentId;
    private String phoneNumber;
    private String email;

    public Student(CreateStudentDto createStudentDto) {
        this.setName(createStudentDto.getName());
        this.setStudentId(createStudentDto.getStudentId());
        this.setPhoneNumber(createStudentDto.getPhoneNumber());
        this.setEmail(createStudentDto.getEmail());
    }
}
