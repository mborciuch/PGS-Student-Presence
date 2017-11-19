package PGS.JAVADEV.PGS.Student.Presence.List.Model;

import javax.persistence.*;

@Table
@Entity
public class StudentSubjectEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private StudentEntity studentEntity;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private SubjectEntity subjectEntity;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private GradeEntity gradeEntity;

    @OneToMany(mappedBy = "studentSubjectEntity")
    private PresenceEntity presenceEntity;

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

/*    public GradeEntity getGradeEntity() {
        return gradeEntity;
    }

    public void setGradeEntity(GradeEntity gradeEntity) {
        this.gradeEntity = gradeEntity;
    }*/
}
