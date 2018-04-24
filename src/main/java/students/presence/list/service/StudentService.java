package students.presence.list.service;

import students.presence.list.dto.StudentDTO;
import students.presence.list.dto.EnrollmentDTO;
import students.presence.list.mapper.CourseMapper;
import students.presence.list.mapper.StudentMapper;
import students.presence.list.model.Student;
import students.presence.list.model.Enrollment;
import students.presence.list.model.Course;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.EnrollmentRepository;
import students.presence.list.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final StudentMapper studentMapper;

    private final CourseMapper courseMapper;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
                          EnrollmentRepository enrollmentRepository,
                          StudentMapper studentMapper, CourseMapper courseMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }

    public StudentDTO findById(long studentId) {
        StudentDTO studentDTO = new StudentDTO();
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            return null;
        }
        studentDTO = studentMapper.mapStudentToStudentDTO(student);
        Set<String> courses = student.getEnrollments().stream()
                .map(el-> el.getCourse().getName())
                .collect(Collectors.toSet());
        studentDTO.setCourseNames(courses);
        return studentDTO;
    }

    public Set<StudentDTO> findAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        Set<StudentDTO> studentDTOS = new HashSet<>();
        students.forEach(student -> studentMapper.mapStudentToStudentDTO(student));
        return studentDTOS;
    }


    public Set<StudentDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        Set<StudentDTO> studentDTOS = new HashSet<>();
        Iterable<Student> studentEntities = studentRepository.findByFirstNameAndLastName(firstName, lastName);
        studentEntities.forEach(student -> studentMapper.mapStudentToStudentDTO(student));
        return studentDTOS;
    }


    public void saveStudent(StudentDTO studentDTO) {
        studentRepository.save(studentMapper.mapStudentDTOToStudent(studentDTO));
    }
    public void updateStudent(long studentId, StudentDTO studentDTO){
        studentDTO.setId(studentId);
        studentRepository.save(studentMapper.mapStudentDTOToStudent(studentDTO));
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public void enrollStudentToCourse(long studentId, long subjectId) {
        Student student = studentRepository.findById(studentId);
        Course course = courseRepository.findById(subjectId);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        student.getEnrollments().add(enrollment);
        enrollmentRepository.save(enrollment);
    }

}
