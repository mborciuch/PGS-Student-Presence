package PGS.JAVADEV.PGS.Student.Presence.List.repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    StudentEntity findById(long id);
    StudentEntity findByFirstNameAndLastName (String firstName, String lastName);
}
