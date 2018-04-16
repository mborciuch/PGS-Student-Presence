package students.presence.list.model;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne
    private Lecturer lecturer;

    @OneToMany(mappedBy = "subject")
    private Set<StudentSubject> studentSubjects = new HashSet<>();

    public Subject() { }

    public Subject(String name, Lecturer lecturer) {
        this.name = name;
        this.lecturer = lecturer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Set<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(Set<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!name.equals(subject.name)) return false;
        return lecturer.equals(subject.lecturer);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lecturer.hashCode();
        return result;
    }
}

