package et.com.gebeya.academicservice.dto;

import et.com.gebeya.academicservice.model.Enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate dob;
    private boolean isActive;
    private String gender;
    private String studentId;
    private String password;
    private String phoneNumber;
    private Role role;
    private BigDecimal price;
}
