package PGS.JAVADEV.PGS.Student.Presence.List.repositories;

import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentSubjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentsSubjectRepository extends CrudRepository<StudentSubjectEntity, Long> {

    StudentSubjectEntity findAllByStudentEntityIdAndSubjectEntityId(Long studentId, Long subjectId);

    StudentSubjectEntity findByStudentEntityId(long id);
}
