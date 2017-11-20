package PGS.JAVADEV.PGS.Student.Presence.List.repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<GradeEntity, Long> {
    GradeEntity findById(long id);
}
