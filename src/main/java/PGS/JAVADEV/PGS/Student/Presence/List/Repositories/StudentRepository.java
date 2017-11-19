package PGS.JAVADEV.PGS.Student.Presence.List.Repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.Model.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    StudentEntity findById(long id);
}
