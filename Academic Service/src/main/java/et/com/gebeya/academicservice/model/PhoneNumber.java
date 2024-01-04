package et.com.gebeya.academicservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber extends BaseModel {
    @Column(name = "phone_number" ,nullable = false)
    private String phoneNumber;
    @ManyToOne()
    @JoinColumn(name = "address_id")
    private Address addressId;






}
