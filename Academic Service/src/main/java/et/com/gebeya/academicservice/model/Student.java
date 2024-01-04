package et.com.gebeya.academicservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import et.com.gebeya.academicservice.dto.SearchRequestDto;
import et.com.gebeya.academicservice.dto.StudentRequestDto;
import et.com.gebeya.academicservice.model.Enumeration.Role;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.BitSet;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass

public class Student extends Person{

    @Column(name = "date_of_birth")
    private LocalDate dob;
    private String gender;
    @Column(name = "student_id" ,nullable = false, unique = true)
    private String studentId;
    private String password;
    private Role role;
    private String phoneNumber;


    @ManyToMany()
    @JoinTable(
            name = "student_guardian",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "guardian_id")
    )
    @JsonIgnore
    private List<Guardian> guardians;

    @ManyToMany()
    @JoinTable(
            name="student_grade_section",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name="grade_section_id")
    )
    @JsonIgnore
    private List<GradeSection> gradeSections;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;
    private BigDecimal price;


    public Student(String firstName, String middleName,String lastName,String email
    , LocalDate dob,
    String gender,
    String studentId,
    String password,
    Role role,
    String phoneNumber,
                   BigDecimal price
) {
        super(firstName, middleName,lastName,email);
        this.role =role;
        this.dob=dob;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.password=password;
        this.studentId=studentId;
        this.price = price;

    }

}
