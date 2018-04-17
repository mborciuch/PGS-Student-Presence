package students.presence.list.repositories;

import students.presence.list.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findById(long id);

    Set<Course> findByName(String name);

    Course save(Course course);

    void deleteByName(String name);


}
