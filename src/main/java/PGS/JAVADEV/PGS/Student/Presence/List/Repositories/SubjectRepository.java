package PGS.JAVADEV.PGS.Student.Presence.List.Repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.Model.SubjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {
    SubjectEntity findById(long id);
    SubjectEntity findByName(String name);


}
