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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GradeEnum getGrade() {
        return grade;
    }

    public void setGrade(GradeEnum grade) {
        this.grade = grade;
    }

    public Set<StudentSubjectEntity> getStudentSubjectEntity() {
        return studentSubjectEntity;
    }

    public void setStudentSubjectEntity(Set<StudentSubjectEntity> studentSubjectEntity) {
        this.studentSubjectEntity = studentSubjectEntity;
    }

    @Override
    public String toString() {
        return "GradeEntity{" +
                "id=" + id +
                ", grade=" + grade +
                '}';
    }
}
