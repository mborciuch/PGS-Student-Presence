package students.presence.list.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.presence.list.model.Lecturer;

import java.util.Set;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer, Long> {

    Lecturer findById(long lecturerId);

    Set<Lecturer> findByFirstNameAndLastName(String firstName, String lastName);
}
