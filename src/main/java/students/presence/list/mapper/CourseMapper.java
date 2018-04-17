package students.presence.list.mapper;

import students.presence.list.dto.CourseDTO;
import students.presence.list.model.Course;
import org.springframework.stereotype.Component;


@Component
public class CourseMapper {
    public Course mapCourseDTOToCourse(CourseDTO courseDTO){
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        return course;

    }

    public CourseDTO mapCourseToCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        return courseDTO;
    }
}
