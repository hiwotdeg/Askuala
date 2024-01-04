package et.com.gebeya.academicservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GradeSection extends BaseModel {

    private int grade;
    private String section;
    @Column(name = "home_room_teacher_id" ,nullable = false)
    private String homeRoomTeacherId;


    @ManyToMany(mappedBy = "gradeSections")
    private List<Student> students;
    @ManyToMany(mappedBy = "gradeSections")
    private List<Subject> subjects;
}
