package students.presence.list.repositories;

import students.presence.list.model.StudentSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsSubjectRepository extends CrudRepository<StudentSubject, Long> {

    StudentSubject findAllByStudentIdAndSubjectId(Long studentId, Long subjectId);

    StudentSubject findByStudentId(long id);

    StudentSubject findByStudentIdAndSubjectId(long studentId, long subjectId);
}
