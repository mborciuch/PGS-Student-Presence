package PGS.JAVADEV.PGS.Student.Presence.List.dto;


import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Presence {
    private long id;

    @NotNull
    @PastOrPresent
    private Date date;

    @NotNull
    private boolean isPresence;

    public Presence(Date date,  boolean presence) {
        this.date = date;
        this.isPresence = presence;
    }

    public Presence() {
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
