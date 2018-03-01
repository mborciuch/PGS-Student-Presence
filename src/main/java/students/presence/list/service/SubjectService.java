package students.presence.list.service;

import students.presence.list.dto.StudentDTO;
import students.presence.list.dto.SubjectDTO;
import students.presence.list.model.GradeEnum;
import students.presence.list.model.StudentSubject;
import students.presence.list.model.Subject;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.StudentsSubjectRepository;
import students.presence.list.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SubjectService {


    private final SubjectRepository subjectRepository;

    private final StudentRepository studentRepository;

    private final StudentsSubjectRepository studentSubjectRepository;

    // private final SubjectMapper subjectMapper;


    public SubjectService(SubjectRepository subjectRepository,
                          StudentRepository studentRepository,
                          StudentsSubjectRepository studentSubjectRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.studentSubjectRepository = studentSubjectRepository;
    }

    public Set<SubjectDTO> findAllSubjects() {
        Iterable<Subject> subjects = subjectRepository.findAll();
        Set<SubjectDTO> subjectDTOS = new HashSet<>();
        subjects.forEach(subject -> subjectDTOS.add(mapSubjectToSubjectDTO(subject)));
        return subjectDTOS;
    }

    public SubjectDTO findById(long id) {
        SubjectDTO subjectDTO = new SubjectDTO();
        Subject subject = subjectRepository.findById(id);
        if (subject == null) {
            return null;
        }
        subjectDTO = mapSubjectToSubjectDTO(subjectRepository.findById(id));
        subjectDTO.setStudentDTOS(getAllStudents(subject));
        return subjectDTO;
    }

    public SubjectDTO findByName(String name) {
        SubjectDTO subjectDTO = new SubjectDTO();
        Subject subject = subjectRepository.findByName(name);
        if (subject == null) {
            return null;
        }
        subjectDTO = mapSubjectToSubjectDTO(subjectRepository.findByName(name));
        subjectDTO.setStudentDTOS(getAllStudents(subject));
        return subjectDTO;
    }

    public void save(SubjectDTO subjectDTO) {
        subjectRepository.save(mapSubjectDTOToSubject(subjectDTO));
    }

    public void delete(long id) {
        subjectRepository.deleteById(id);
    }

    public void addStudentToSubject(long studentId, long subjectId) {
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setStudent(studentRepository.findById(studentId));
        studentSubject.setSubject(subjectRepository.findById(subjectId));
        studentSubjectRepository.save(studentSubject);
    }

    public void addGradeToStudent(long studentId, long subjectId, GradeEnum grade) {
        StudentSubject studentSubject = studentSubjectRepository.findAllByStudentIdAndSubjectId(studentId, subjectId);
        studentSubject.setGradeEnum(grade);
        studentSubjectRepository.save(studentSubject);
    }

    public boolean isSubjectExist(SubjectDTO subjectDTO) {
        Subject subject = subjectRepository.findByName(subjectDTO.getName());
        return subject != null;
    }

    private Set<StudentDTO> getAllStudents(Subject subject) {
        Set<StudentDTO> studentDTOS = new HashSet<>();
        subject.getStudentSubjects().forEach(el -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(el.getStudent().getFirstName());
            studentDTO.setLastName(el.getStudent().getLastName());
            studentDTOS.add(studentDTO);
        });
        return studentDTOS;
    }

    public Subject mapSubjectDTOToSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        subject.setLecturer(subjectDTO.getLecturer());
        return subject;
    }

    public SubjectDTO mapSubjectToSubjectDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setLecturer(subject.getLecturer());
        return subjectDTO;
    }
}
