package PGS.JAVADEV.PGS.Student.Presence.List.dto;


import java.text.SimpleDateFormat;

public class Presence {
    private long id;
    private String date;
    private boolean presence;

    public Presence(String date,  boolean presence) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

}
