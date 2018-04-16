package students.presence.list.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "student_subject_id")
    private StudentSubject studentSubject;

    private boolean presence;

    private Date date;

    public Presence() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presence presence1 = (Presence) o;

        if (presence != presence1.presence) return false;
        if (!studentSubject.equals(presence1.studentSubject)) return false;
        return date.equals(presence1.date);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (presence ? 1 : 0);
        result = 31 * result + date.hashCode();
        return result;
    }
}
