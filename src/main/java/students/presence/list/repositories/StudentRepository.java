package students.presence.list.repositories;

import students.presence.list.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findById(long id);

    Set<Student> findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByFirstNameAndLastName(String firstName, String lastName);
}
