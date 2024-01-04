package et.com.gebeya.academicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseModel{

    private String email;
    private String city;
    @Column(name = "sub_city")
    private String subCity;
    private String wereda;
    @Column(name = "house_number")
    private String houseNumber;


    @OneToMany(mappedBy = "addressId" ,cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;
    @OneToOne(mappedBy = "address")
    private Student student;
    @OneToOne(mappedBy = "address")
    private Guardian guardian;

}
