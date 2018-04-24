package students.presence.list.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.presence.list.model.Lecture;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LectureRepository extends CrudRepository< Lecture, Long>{

    Lecture findById(long lectureId);

    Set<Lecture> findByName(String lectureName);

    Lecture save(Lecture lecture);


}
