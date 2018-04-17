package students.presence.list.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.presence.list.model.Lecture;

@Repository
public interface LectureRepository extends CrudRepository<Long, Lecture>{

    Lecture findById(long lectureId);
}
