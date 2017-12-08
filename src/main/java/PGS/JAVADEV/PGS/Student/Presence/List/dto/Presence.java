package PGS.JAVADEV.PGS.Student.Presence.List.dto;


import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.PastOrPresent;
import java.text.SimpleDateFormat;

public class Presence {
    private long id;

    @PastOrPresent
    private SimpleDateFormat date;

    private boolean presence;

    public Presence(SimpleDateFormat date,  boolean presence) {
        this.date = date;
        this.presence = presence;
    }

    public Presence() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

}
