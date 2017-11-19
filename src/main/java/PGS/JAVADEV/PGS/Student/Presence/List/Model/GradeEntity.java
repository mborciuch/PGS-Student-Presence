package PGS.JAVADEV.PGS.Student.Presence.List.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private GradeEnum grade;

    @OneToMany(mappedBy = "gradeEntity")
    private Set<StudentSubjectEntity> studentSubjectEntity = new HashSet<>();

    @Override
    public String toString() {
        return "GradeEntity{" +
                "id=" + id +
                ", grade=" + grade +
                '}';
    }
}
