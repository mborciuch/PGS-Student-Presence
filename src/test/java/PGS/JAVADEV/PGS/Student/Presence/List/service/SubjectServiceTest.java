package PGS.JAVADEV.PGS.Student.Presence.List.service;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.mapper.SubjectMapper;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentSubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.StudentRepository;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.StudentsSubjectRepository;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.SubjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEnum.A;
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

    @Before
    public void setUp() throws Exception {


        subjectService = new SubjectService(
                subjectRepository,
                studentRepository,
                studentSubjectRepository

        );
    }

    @Test
    public void findAllSubjects() throws Exception {
        SubjectEntity subjectEntity = new SubjectEntity();

        Set<SubjectEntity> subjectEntities = new HashSet<>();
        subjectEntities.add(subjectEntity);

        when(subjectRepository.findAll()).thenReturn(subjectEntities);

        Set<Subject> subjects = subjectService.findAllSubjects();

        assertEquals(subjects.size(),1);
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    public void findById() throws Exception {

        //Given

        SubjectEntity subjectEntityFirst = new SubjectEntity();
        subjectEntityFirst.setId(ID_1);

        StudentEntity studentEntityFirst = new StudentEntity();
        studentEntityFirst.setId(ID_1);

        StudentEntity studentEntitySecond = new StudentEntity();
        studentEntityFirst.setId(ID_2);

        StudentSubjectEntity studentSubjectEntityFirst = new StudentSubjectEntity();
        studentEntityFirst.setId(ID_1);
        studentSubjectEntityFirst.setSubjectEntity(subjectEntityFirst);
        studentSubjectEntityFirst.setStudentEntity(studentEntityFirst);

        StudentSubjectEntity studentSubjectEntitySecond = new StudentSubjectEntity();
        studentEntitySecond.setId(ID_2);
        studentSubjectEntityFirst.setSubjectEntity(subjectEntityFirst);
        studentSubjectEntitySecond.setStudentEntity(studentEntitySecond);

        Set<StudentSubjectEntity> studentSubjectEntities = new HashSet<>();
        studentSubjectEntities.add(studentSubjectEntityFirst);
        studentSubjectEntities.add(studentSubjectEntitySecond);

        subjectEntityFirst.setStudentSubjectEntities(studentSubjectEntities);

        when(subjectRepository.findById(ID_1)).thenReturn(subjectEntityFirst);

        //When

        Subject subjectFirst = subjectService.findById(ID_1);
         Set<Student> students =  subjectFirst.getStudents();

         //Then

        assertNotNull(subjectFirst);
        assertEquals(2, students.size());
        verify(subjectRepository, times(2)).findById(ID_1);
    }



    @Test
    public void findByName() throws Exception {

        //Given
        SubjectEntity subjectEntityFirst = new SubjectEntity();
        subjectEntityFirst.setName(SUBJECT_MATH);


        when(subjectRepository.findByName(SUBJECT_MATH)).thenReturn(subjectEntityFirst);


        //When
        Subject subject =subjectService.findByName(SUBJECT_MATH);

        //Then
        assertEquals(subject.getName(),SUBJECT_MATH );

    }

    @Test
    public void save() throws Exception {

        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setName(SUBJECT_MATH);

        SubjectEntity subjectEntityFirst = new SubjectEntity();
        subjectEntityFirst.setName(subjectFirst.getName());

        // When
        subjectService.save(subjectFirst);

        //then
        verify(subjectRepository, times(1)).save(subjectEntityFirst);
        assertEquals(subjectFirst.getName(), subjectEntityFirst.getName());




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

        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(id);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(id);

        StudentSubjectEntity studentSubjectEntity = new StudentSubjectEntity();
        studentSubjectEntity.setSubjectEntity(subjectEntity);
        studentSubjectEntity.setStudentEntity(studentEntity);

        //When

        subjectService.addStudentToSubject(studentSubjectEntity.getStudentEntity().getId(),studentSubjectEntity.getSubjectEntity().getId());

        //Then

        verify(studentSubjectRepository, times(1)).save(any());


    }

    @Test
    public void addGradeToStudent() throws Exception {

        // Given

        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(ID_1);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(ID_1);



        StudentSubjectEntity studentSubjectEntity = new StudentSubjectEntity();
        studentSubjectEntity.setSubjectEntity(subjectEntity);
        studentSubjectEntity.setStudentEntity(studentEntity);
        studentSubjectEntity.setGradeEnum(A);

        //When

        when(studentSubjectRepository.findAllByStudentEntityIdAndSubjectEntityId(ID_1,ID_1)).thenReturn(studentSubjectEntity);
        subjectService.addGradeToStudent(
                studentSubjectEntity.getStudentEntity().getId(),
                studentSubjectEntity.getSubjectEntity().getId(),
                studentSubjectEntity.getGradeEnum()
        );

        //Then

        verify(studentSubjectRepository, times(1)).save(any());
    }




}