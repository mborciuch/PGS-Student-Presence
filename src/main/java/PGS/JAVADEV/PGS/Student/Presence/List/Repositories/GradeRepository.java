package PGS.JAVADEV.PGS.Student.Presence.List.Repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.Model.GradeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<GradeEntity, Long> {
    GradeEntity findById(long id);
}
