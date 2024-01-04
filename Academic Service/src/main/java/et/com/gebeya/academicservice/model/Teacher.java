package et.com.gebeya.academicservice.model;

import et.com.gebeya.academicservice.model.Enumeration.Role;
import et.com.gebeya.academicservice.model.Enumeration.TeacherQualification;
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
public class Teacher extends Person{

    @Enumerated(EnumType.STRING)
    private TeacherQualification qualification;
    private char gender;
    @Column(name = "teacher_id" ,nullable = false, unique = true)
    private String teacherId;
    private String password;
    private Role role;
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

}
