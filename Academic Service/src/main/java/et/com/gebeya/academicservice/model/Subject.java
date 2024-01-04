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
public class Subject extends BaseModel {

    @Column(name = "course_title" ,nullable = false)
    private String courseName;


    @ManyToMany()
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name= "teacher_id")
    )
    private List<Teacher> teachers;

    @ManyToMany()
    @JoinTable(
            name = "grade_section_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name="grade_section_id")
    )
    private List<GradeSection> gradeSections;

}
