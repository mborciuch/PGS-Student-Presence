package PGS.JAVADEV.PGS.Student.Presence.List.Repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.Model.GradeEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.Model.PresenceEntity;
import org.springframework.data.repository.CrudRepository;


public interface PresenceRepository extends CrudRepository<PresenceEntity, Long> {
    PresenceEntity findById(long id);
}

