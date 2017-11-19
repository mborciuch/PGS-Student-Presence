package PGS.JAVADEV.PGS.Student.Presence.List.Model;

import javax.persistence.*;

@Entity
public class PresenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_subject_entity_id")
    private StudentSubjectEntity studentSubjectEntity;

    private boolean presence;

    private String date;

    public PresenceEntity() {
    }

    public PresenceEntity(boolean presence, String date) {
        this.presence = presence;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
