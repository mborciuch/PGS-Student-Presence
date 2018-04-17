package students.presence.list.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LecturerDTO {

    private long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255)
    private String lastName;
}
