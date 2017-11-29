package PGS.JAVADEV.PGS.Student.Presence.List.repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {
    SubjectEntity findById(long id);
    SubjectEntity findByName(String name);

    SubjectEntity save(SubjectEntity subjectEntity);
    void deleteByName(String name);


}
