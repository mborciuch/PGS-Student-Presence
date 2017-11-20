package PGS.JAVADEV.PGS.Student.Presence.List.model;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Entity
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    private String name;
    private String lecturer;
    @OneToMany(mappedBy = "subjectEntity")
    private Set<StudentSubjectEntity> studentSubjectEntities = new HashSet<>();


    public SubjectEntity() {
    }

    public SubjectEntity(String name, String lecturer) {
        this.name = name;
        this.lecturer = lecturer;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Set<StudentSubjectEntity> getStudentSubjectEntities() {
        return studentSubjectEntities;
    }

    public void setStudentSubjectEntities(Set<StudentSubjectEntity> studentSubjectEntities) {
        this.studentSubjectEntities = studentSubjectEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectEntity that = (SubjectEntity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lecturer='" + lecturer + '\'' +
                '}';
    }
}

