package students.presence.list.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "studentSubject")
    private Set<Presence> presences = new HashSet<>();

    private GradeEnum gradeEnum;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public GradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(GradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    public Set<Presence> getPresence() {
        return presences;
    }

    public void setPresence(Set<Presence> presence) {
        this.presences = presence;
    }
}
