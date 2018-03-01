package students.presence.list.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class StudentDTO {

    private  long id;
    @NotNull
    @Size(min = 1, max = 255)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 255)
    private String lastName;

    private Set<StudentSubjectDTO> subjects = new HashSet<>();


    public StudentDTO(){

    };
    public StudentDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<StudentSubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<StudentSubjectDTO> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
