package et.com.gebeya.academicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private boolean is_Active;
}
