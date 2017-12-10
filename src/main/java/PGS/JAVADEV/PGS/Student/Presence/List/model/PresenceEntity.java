package PGS.JAVADEV.PGS.Student.Presence.List.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class PresenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_subject_entity_id")
    private StudentSubjectEntity studentSubjectEntity;

    private boolean presence;

    private Date date;

    public PresenceEntity() {
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

        PresenceEntity that = (PresenceEntity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "PresenceEntity{" +
                "id=" + id +
                ", studentSubjectEntity=" + studentSubjectEntity +
                ", presence=" + presence +
                ", date='" + date + '\'' +
                '}';
    }
}
