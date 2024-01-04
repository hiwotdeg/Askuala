package et.com.gebeya.asqualacomservice.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private String name;
    private Long age;
    private String phoneNumber;
    private String email;
}
