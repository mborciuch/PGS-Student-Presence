package students.presence.list.service;

import students.presence.list.dto.StudentDTO;
import students.presence.list.dto.StudentSubjectDTO;
import students.presence.list.model.Student;
import students.presence.list.model.StudentSubject;
import students.presence.list.model.Subject;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.StudentsSubjectRepository;
import students.presence.list.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentsSubjectRepository studentsSubjectRepository;


    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository,
                          StudentsSubjectRepository studentsSubjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.studentsSubjectRepository = studentsSubjectRepository;
    }

    public Set<StudentDTO> findAllStudents() {
        Iterable<Student> studentEntities = studentRepository.findAll();
        Set<StudentDTO> studentDTOS = new HashSet<>();
        studentEntities.forEach(student -> studentDTOS.add(mapStudentToStudentDTO(student)));
        return studentDTOS;
    }

    public StudentDTO findById(long id) {
        StudentDTO studentDTO = new StudentDTO();
        Student student = studentRepository.findById(id);
        if (student == null) {
            return null;
        }
        studentDTO = mapStudentToStudentDTO(studentRepository.findById(id));
        studentDTO.setSubjects(getAllSubjects(student));
        return studentDTO;
    }

    public StudentDTO findByFirstNameAndLastName(String firstName, String lastName) {
        StudentDTO studentDTO = new StudentDTO();
        Student student = studentRepository.findByFirstNameAndLastName(firstName, lastName);
        if (student == null) {
            return null;
        }
        studentDTO = mapStudentToStudentDTO(studentRepository.findByFirstNameAndLastName(firstName, lastName));
        studentDTO.setSubjects(getAllSubjects(student));
        return studentDTO;
    }


    public void save(StudentDTO studentDTO) {
        studentRepository.save(mapStudentDTOToStudent(studentDTO));
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public boolean isStudentExist(StudentDTO studentDTO) {
        Student student = studentRepository.findByFirstNameAndLastName(studentDTO.getFirstName(), studentDTO.getLastName());
        return student != null;
    }

    public void addSubjectToStudent(long studentId, long subjectId) {
        Student student = studentRepository.findById(studentId);
        Subject subject = subjectRepository.findById(subjectId);
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setStudent(student);
        studentSubject.setSubject(subject);
        student.getStudentSubjects().add(studentSubject);
        studentsSubjectRepository.save(studentSubject);
    }

    private Set<StudentSubjectDTO> getAllSubjects(Student student) {
        Set<StudentSubjectDTO> studentSubjectDTOS = new HashSet<>();
        student.getStudentSubjects().forEach(el -> {
            StudentSubjectDTO studentSubjectDTO = new StudentSubjectDTO();
            studentSubjectDTO.setGrade(el.getGradeEnum());
            studentSubjectDTO.setSubjectId(el.getSubject().getId());
            studentSubjectDTO.setSubjectName(el.getSubject().getName());
            studentSubjectDTO.setLecturer(el.getSubject().getLecturer());
            studentSubjectDTOS.add(studentSubjectDTO);
        });
        return studentSubjectDTOS;
    }

    public Student mapStudentDTOToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        return student;
    }

    public StudentDTO mapStudentToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        return studentDTO;
    }


}
