package students.presence.list.dto;


import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PresenceDTO {
    private long id;

    @NotNull
    @PastOrPresent
    private Date date;

    @NotNull
    private boolean isPresence;

    public PresenceDTO(Date date, boolean presence) {
        this.date = date;
        this.isPresence = presence;
    }

    public PresenceDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPresence() {
        return isPresence;
    }

    public void setPresence(boolean presence) {
        isPresence = presence;
    }
}
