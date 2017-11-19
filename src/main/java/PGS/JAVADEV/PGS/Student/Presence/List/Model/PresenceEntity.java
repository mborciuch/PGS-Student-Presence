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


}
