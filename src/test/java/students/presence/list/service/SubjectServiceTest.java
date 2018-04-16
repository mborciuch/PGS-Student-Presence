package students.presence.list.service;

import students.presence.list.dto.StudentDTO;
import students.presence.list.dto.SubjectDTO;
import students.presence.list.mapper.SubjectMapper;
import students.presence.list.model.Lecturer;
import students.presence.list.model.Student;
import students.presence.list.model.StudentSubject;
import students.presence.list.model.Subject;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.StudentsSubjectRepository;
import students.presence.list.repositories.SubjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static students.presence.list.model.GradeEnum.A;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {

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
    SubjectService subjectService;

    @Mock
    SubjectRepository subjectRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentsSubjectRepository studentSubjectRepository;

    @Mock
    SubjectMapper subjectMapper;

    Lecturer lecturer;

    @Before
    public void setUp() throws Exception {
        subjectService = new SubjectService(
                subjectRepository,
                studentRepository,
                studentSubjectRepository
        );
        lecturer = new Lecturer();
        lecturer.setFirstName(FIRST_NAME_LECTURER);
        lecturer.setLastName(LAST_NAME_LECTURER);
    }

    @Test
    public void findAllSubjects() throws Exception {
        Subject subject = new Subject();
        subject.setName(SUBJECT_MATH);
        subject.setLecturer(lecturer);

        Set<Subject> subjectEntities = new HashSet<>();
        subjectEntities.add(subject);

        when(subjectRepository.findAll()).thenReturn(subjectEntities);

        Set<SubjectDTO> subjectDTOS = subjectService.findAllSubjects();

        assertEquals(subjectDTOS.size(), 1);
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    public void findById() throws Exception {

        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);

        Student studentFirst = new Student();
        studentFirst.setId(ID_1);

        Student studentSecond = new Student();
        studentFirst.setId(ID_2);

        StudentSubject studentSubjectFirst = new StudentSubject();
        studentFirst.setId(ID_1);
        studentSubjectFirst.setSubject(subjectFirst);
        studentSubjectFirst.setStudent(studentFirst);

        StudentSubject studentSubjectSecond = new StudentSubject();
        studentSecond.setId(ID_2);
        studentSubjectFirst.setSubject(subjectFirst);
        studentSubjectSecond.setStudent(studentSecond);

        Set<StudentSubject> studentSubjectEntities = new HashSet<>();
        studentSubjectEntities.add(studentSubjectFirst);
        studentSubjectEntities.add(studentSubjectSecond);

        subjectFirst.setStudentSubjects(studentSubjectEntities);

        when(subjectRepository.findById(ID_1)).thenReturn(subjectFirst);

        //When
        SubjectDTO subjectDTOFirst = subjectService.findById(ID_1);
        Set<StudentDTO> studentDTOS = subjectDTOFirst.getStudentDTOS();

        //Then
        assertNotNull(subjectDTOFirst);
        assertEquals(2, studentDTOS.size());
        verify(subjectRepository, times(2)).findById(ID_1);
    }


    @Test
    public void findByName() throws Exception {

        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setName(SUBJECT_MATH);


        when(subjectRepository.findByName(SUBJECT_MATH)).thenReturn(subjectFirst);


        //When
        SubjectDTO subjectDTO = subjectService.findByName(SUBJECT_MATH);

        //Then
        assertEquals(subjectDTO.getName(), SUBJECT_MATH);

    }

    @Test
    public void save() throws Exception {

        //Given
        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setName(SUBJECT_MATH);

        Subject subjectFirst = new Subject();
        subjectFirst.setName(subjectDTOFirst.getName());

        // When
        subjectService.save(subjectDTOFirst);

        //then
        verify(subjectRepository, times(1)).save(subjectFirst);
        assertEquals(subjectDTOFirst.getName(), subjectFirst.getName());
    }

    @Test
    public void delete() throws Exception {

        //Given
        Long id = ID_1;

        //When
        subjectService.delete(id);

        //Then
        verify(subjectRepository, times(1)).deleteById(anyLong());

    }

    @Test
    public void addStudentToSubject() throws Exception {

        // Given
        Long id = 1l;

        Subject subject = new Subject();
        subject.setId(id);

        Student student = new Student();
        student.setId(id);

        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setSubject(subject);
        studentSubject.setStudent(student);

        //When
        subjectService.addStudentToSubject(studentSubject.getStudent().getId(), studentSubject.getSubject().getId());

        //Then
        verify(studentSubjectRepository, times(1)).save(any());


    }

    @Test
    public void addGradeToStudent() throws Exception {

        // Given
        Subject subject = new Subject();
        subject.setId(ID_1);

        Student student = new Student();
        student.setId(ID_1);


        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setSubject(subject);
        studentSubject.setStudent(student);
        studentSubject.setGradeEnum(A);

        //When
        when(studentSubjectRepository.findAllByStudentIdAndSubjectId(ID_1, ID_1)).thenReturn(studentSubject);
        subjectService.addGradeToStudent(
                studentSubject.getStudent().getId(),
                studentSubject.getSubject().getId(),
                studentSubject.getGradeEnum()
        );

        //Then
        verify(studentSubjectRepository, times(1)).save(any());
    }


}