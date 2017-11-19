package PGS.JAVADEV.PGS.Student.Presence.List.Model;

import PGS.JAVADEV.PGS.Student.Presence.List.DTO.Student;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Entity
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @Column
    private String name;


    @OneToMany(mappedBy = "subjectEntity")
    private Set<StudentSubjectEntity> studentSubjectEntities = new HashSet<>();



    public SubjectEntity(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectEntity that = (SubjectEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
