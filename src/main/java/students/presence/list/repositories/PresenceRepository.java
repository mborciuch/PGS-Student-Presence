package students.presence.list.repositories;

import students.presence.list.model.Presence;
import students.presence.list.model.StudentSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PresenceRepository extends CrudRepository<Presence, Long> {

    Presence findById(long id);

    Presence findByStudentSubjectAndDate(StudentSubject studentSubject, Date date);
}

