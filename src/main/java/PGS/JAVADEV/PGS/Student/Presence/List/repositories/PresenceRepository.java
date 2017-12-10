package PGS.JAVADEV.PGS.Student.Presence.List.repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.model.PresenceEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentSubjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PresenceRepository extends CrudRepository<PresenceEntity, Long> {

    PresenceEntity findById(long id);

    PresenceEntity findByStudentSubjectEntityAndDate (StudentSubjectEntity studentSubjectEntity, Date date);
}

