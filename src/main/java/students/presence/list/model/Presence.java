package students.presence.list.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_subject_id")
    private StudentSubject studentSubject;

    private boolean presence;

    private Date date;

    public Presence() {
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

        Presence that = (Presence) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Presence{" +
                "id=" + id +
                ", studentSubject=" + studentSubject +
                ", presence=" + presence +
                ", date='" + date + '\'' +
                '}';
    }
}
