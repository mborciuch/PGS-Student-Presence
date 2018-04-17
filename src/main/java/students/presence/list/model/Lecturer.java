package students.presence.list.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany
    private Set<Course> course;
}
