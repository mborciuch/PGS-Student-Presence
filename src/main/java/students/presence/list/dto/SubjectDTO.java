package students.presence.list.dto;

import students.presence.list.model.Lecturer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class SubjectDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    private Lecturer lecturer;

    private Set < StudentDTO > studentDTOS = new HashSet < > ();

    public SubjectDTO() {}

    public SubjectDTO(String name, Lecturer lecturer) {
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

    public Set < StudentDTO > getStudentDTOS() {
        return studentDTOS;
    }

    public void setStudentDTOS(Set < StudentDTO > studentDTOS) {
        this.studentDTOS = studentDTOS;
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", studentDTOS=" + studentDTOS +
                '}';
    }
}


