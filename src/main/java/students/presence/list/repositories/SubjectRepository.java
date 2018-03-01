package students.presence.list.repositories;

import students.presence.list.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Subject findById(long id);

    Subject findByName(String name);

    Subject save(Subject subject);

    void deleteByName(String name);


}
