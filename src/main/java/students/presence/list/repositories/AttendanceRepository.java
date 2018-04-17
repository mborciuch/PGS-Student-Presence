package students.presence.list.repositories;

import students.presence.list.model.Attendance;
import students.presence.list.model.Enrollment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    Attendance findById(long id);

    Attendance saveAttendance(Attendance attendance);

    Attendance findByStudentIdAndLectureId(long studentId, long courseId);

    Attendance deleteByStudentIdAndLectureId(long studentId, long courseId );
}

