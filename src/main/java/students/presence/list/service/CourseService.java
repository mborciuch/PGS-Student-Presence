package students.presence.list.service;

import students.presence.list.dto.CourseDTO;
import students.presence.list.dto.StudentDTO;
import students.presence.list.mapper.CourseMapper;
import students.presence.list.model.Grade;
import students.presence.list.model.Enrollment;
import students.presence.list.model.Course;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.EnrollmentRepository;
import students.presence.list.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CourseService {


    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final CourseMapper courseMapper;


    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository,
                         EnrollmentRepository enrollmentRepository,
                         CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.courseMapper = courseMapper;
    }

    public Set<CourseDTO> findAllSubjects() {
        Iterable<Course> courses = courseRepository.findAll();
        Set<CourseDTO> courseDTOS = new HashSet<>();
        courses.forEach(course -> courseDTOS.add(courseMapper.mapCourseToCourseDTO(course)));
        return courseDTOS;
    }

    public CourseDTO findById(long courseId) {
        CourseDTO courseDTO = new CourseDTO();
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            return null;
        }
        courseDTO = courseMapper.mapCourseToCourseDTO(courseRepository.findById(courseId));
        courseDTO.setStudentDTOS(getAllStudents(course));
        return courseDTO;
    }

    public Set<CourseDTO> findByName(String name) {
        Iterable<Course> courses = courseRepository.findByName(name);
        Set<CourseDTO> courseDTOS = new HashSet<>();
        courses.forEach(course -> courseDTOS.add(courseMapper.mapCourseToCourseDTO(course)));
        return courseDTOS;
    }

    public void saveCourse(CourseDTO courseDTO) {
        courseRepository.save(courseMapper.mapCourseDTOToCourse(courseDTO));
    }

    public void updateCourse(long courseId, CourseDTO courseDTO){
        courseDTO.setId(courseId);
        courseRepository.save(courseMapper.mapCourseDTOToCourse(courseDTO));
    }

    public void deleteCourse(long courseId) {
        courseRepository.deleteById(courseId);
    }


    public void addStudentToCourse(long studentId, long courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentRepository.findById(studentId));
        enrollment.setCourse(courseRepository.findById(courseId));
        enrollmentRepository.save(enrollment);

    }

    public void addGradeToStudent(long studentId, long subjectId, Grade grade) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, subjectId);
        enrollment.setFinalGrade(grade);
        enrollmentRepository.save(enrollment);
    }


    private Set<StudentDTO> getAllStudents(Course course) {
        Set<StudentDTO> studentDTOS = new HashSet<>();
        course.getEnrollments().forEach(el -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(el.getStudent().getFirstName());
            studentDTO.setLastName(el.getStudent().getLastName());
            studentDTOS.add(studentDTO);
        });
        return studentDTOS;
    }

}
