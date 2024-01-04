package et.com.gebeya.academicservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Builder
public class Person extends BaseModel{

    @Column(name = "first_name" , nullable = false)
    private String firstName;
    @Column(name = "middle_name" , nullable = false)
    private String middleName;
    @Column(name = "last_name" , nullable = false)
    private String lastName;
    @Column(name = "email")
    private String email;



}
