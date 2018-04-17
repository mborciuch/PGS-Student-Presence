package students.presence.list.repositories;

import students.presence.list.model.Enrollment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {

    Enrollment findByStudentIdAndCourseId(long studentId, long subjectId);

    Enrollment findByStudentIdAndSubjectId(long studentId, long subjectId);
}
