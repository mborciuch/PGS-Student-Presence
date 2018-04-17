package students.presence.list.dto;

import students.presence.list.model.Lecturer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class CourseDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    private String description;

    private LectureDTO lectureDTO;

    private Set<StudentDTO> studentDTOS;

    public CourseDTO() {

    }

    public CourseDTO(@NotNull @Size(min = 1, max = 255) String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StudentDTO> getStudentDTOS() {
        return studentDTOS;
    }

    public void setStudentDTOS(Set<StudentDTO> studentDTOS) {
        this.studentDTOS = studentDTOS;
    }

    public LectureDTO getLectureDTO() {
        return lectureDTO;
    }

    public void setLectureDTO(LectureDTO lectureDTO) {
        this.lectureDTO = lectureDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseDTO courseDTO = (CourseDTO) o;

        if (name != null ? !name.equals(courseDTO.name) : courseDTO.name != null) return false;
        return description != null ? description.equals(courseDTO.description) : courseDTO.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


}


