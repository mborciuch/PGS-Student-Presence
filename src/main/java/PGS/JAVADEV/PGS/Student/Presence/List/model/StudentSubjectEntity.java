package PGS.JAVADEV.PGS.Student.Presence.List.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class StudentSubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private StudentEntity studentEntity;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private SubjectEntity subjectEntity;

    @OneToMany(mappedBy = "studentSubjectEntity")
    private Set<PresenceEntity> presenceEntity = new HashSet<>();

    private GradeEnum gradeEnum;

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public void setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
    }

 public GradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(GradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    public Set<PresenceEntity> getPresenceEntity() {
        return presenceEntity;
    }

    public void setPresenceEntity(Set<PresenceEntity> presenceEntity) {
        this.presenceEntity = presenceEntity;
    }
}
