package students.presence.list.service;

import students.presence.list.dto.CourseDTO;
import students.presence.list.dto.StudentDTO;
import students.presence.list.mapper.CourseMapper;
import students.presence.list.model.Lecturer;
import students.presence.list.model.Student;
import students.presence.list.model.Enrollment;
import students.presence.list.model.Course;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.EnrollmentRepository;
import students.presence.list.repositories.CourseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static students.presence.list.model.Grade.A;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

    public static final long ID_1 = 1L;

    public static final String FIRST_NAME_1 = "Jan";

    public static final String LAST_NAME_1 = "Kowalski";

    public static final long ID_2 = 2L;

    public static final String FIRST_NAME_2 = "Marian";

    public static final String LAST_NAME_2 = "Nowak";

    public static final String SUBJECT_MATH = "Matematyka";

    public static final String FIRST_NAME_LECTURER = "Marcin";

    public static final String LAST_NAME_LECTURER = "Nowak";

    private final long emptySize = 0;

    @Autowired
    CourseService courseService;

    @Mock
    CourseRepository courseRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    EnrollmentRepository studentSubjectRepository;

    @Mock
    CourseMapper courseMapper;

    Lecturer lecturer;

    @Before
    public void setUp() throws Exception {
  /*      courseService = new CourseService(
                courseRepository,
                studentRepository,
                studentSubjectRepository
        );
        lecturer = new Lecturer();
        lecturer.setFirstName(FIRST_NAME_LECTURER);
        lecturer.setLastName(LAST_NAME_LECTURER);
    }

    @Test
    public void findAllSubjects() throws Exception {
        Course course = new Course();
        course.setName(SUBJECT_MATH);
        course.setLecturer(lecturer);

        Set<Course> courseEntities = new HashSet<>();
        courseEntities.add(course);

        when(courseRepository.findAll()).thenReturn(courseEntities);

        Set<CourseDTO> courseDTOS = courseService.findAllSubjects();

        assertEquals(courseDTOS.size(), 1);
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void findById() throws Exception {

        //Given
        Course courseFirst = new Course();
        courseFirst.setId(ID_1);

        Student studentFirst = new Student();
        studentFirst.setId(ID_1);

        Student studentSecond = new Student();
        studentFirst.setId(ID_2);

        Enrollment enrollmentFirst = new Enrollment();
        studentFirst.setId(ID_1);
        enrollmentFirst.setCourse(courseFirst);
        enrollmentFirst.setStudent(studentFirst);

        Enrollment enrollmentSecond = new Enrollment();
        studentSecond.setId(ID_2);
        enrollmentFirst.setCourse(courseFirst);
        enrollmentSecond.setStudent(studentSecond);

        Set<Enrollment> enrollmentEntities = new HashSet<>();
        enrollmentEntities.add(enrollmentFirst);
        enrollmentEntities.add(enrollmentSecond);

        courseFirst.setEnrollments(enrollmentEntities);

        when(courseRepository.findById(ID_1)).thenReturn(courseFirst);

        //When
        CourseDTO courseDTOFirst = courseService.findById(ID_1);
        Set<StudentDTO> studentDTOS = courseDTOFirst.getStudentDTOS();

        //Then
        assertNotNull(courseDTOFirst);
        assertEquals(2, studentDTOS.size());
        verify(courseRepository, times(2)).findById(ID_1);
    }


    @Test
    public void findByName() throws Exception {

        //Given
        Course courseFirst = new Course();
        courseFirst.setName(SUBJECT_MATH);


        when(courseRepository.findByName(SUBJECT_MATH)).thenReturn(courseFirst);


        //When
        CourseDTO courseDTO = courseService.findByName(SUBJECT_MATH);

        //Then
        assertEquals(courseDTO.getName(), SUBJECT_MATH);

    }

    @Test
    public void save() throws Exception {

        //Given
        CourseDTO courseDTOFirst = new CourseDTO();
        courseDTOFirst.setName(SUBJECT_MATH);

        Course courseFirst = new Course();
        courseFirst.setName(courseDTOFirst.getName());

        // When
        courseService.saveCourse(courseDTOFirst);

        //then
        verify(courseRepository, times(1)).save(courseFirst);
        assertEquals(courseDTOFirst.getName(), courseFirst.getName());
    }

    @Test
    public void delete() throws Exception {

        //Given
        Long id = ID_1;

        //When
        courseService.deleteCourse(id);

        //Then
        verify(courseRepository, times(1)).deleteById(anyLong());

    }

    @Test
    public void addStudentToSubject() throws Exception {

        // Given
        Long id = 1l;

        Course course = new Course();
        course.setId(id);

        Student student = new Student();
        student.setId(id);

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        //When
        courseService.addStudentToCourse(enrollment.getStudent().getId(), enrollment.getCourse().getId());

        //Then
        verify(studentSubjectRepository, times(1)).save(any());


    }

    @Test
    public void addGradeToStudent() throws Exception {

        // Given
        Course course = new Course();
        course.setId(ID_1);

        Student student = new Student();
        student.setId(ID_1);


        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setGradeEnum(A);

        //When
        when(studentSubjectRepository.findByStudentIdAndCourseId(ID_1, ID_1)).thenReturn(enrollment);
        courseService.addGradeToStudent(
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId(),
                enrollment.getGradeEnum()
        );

        //Then
        verify(studentSubjectRepository, times(1)).save(any());*/
    }


}