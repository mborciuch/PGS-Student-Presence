package students.presence.list.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.presence.list.model.Lecturer;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer, Long> {
}
