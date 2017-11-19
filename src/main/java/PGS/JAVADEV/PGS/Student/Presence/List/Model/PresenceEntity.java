package PGS.JAVADEV.PGS.Student.Presence.List.Model;

import javax.persistence.*;

@Entity
public class PresenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "Student_Subject_Entity_id")
    private StudentSubjectEntity studentSubjectEntity;

    private boolean presence;

    private String date;


}
