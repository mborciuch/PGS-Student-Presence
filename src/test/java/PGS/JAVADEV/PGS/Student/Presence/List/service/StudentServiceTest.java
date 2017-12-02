package PGS.JAVADEV.PGS.Student.Presence.List.service;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
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
import org.yaml.snakeyaml.events.Event;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    public static final long ID_1 = 1l;
    public static final String STUDENT1_FIRST_NAME = "Jan";
    public static final String STUDENT1_LAST_NAME = "Kowalski";
    @Autowired
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Mock
    SubjectRepository subjectRepository;

    @Mock
    StudentsSubjectRepository studentsSubjectRepository;

    @Before
    public void setUp() throws Exception {


        studentService = new StudentService(
                studentRepository,subjectRepository,studentsSubjectRepository
        );
    }
    @Test
    public void findAllStudents() throws Exception {

        //Given
        StudentEntity studentEntityFirst = new StudentEntity();
        StudentEntity studentEntitySecond = new StudentEntity();
        Set<StudentEntity> studentEntities = new HashSet<>();
        studentEntities.add(studentEntityFirst);
        studentEntities.add(studentEntitySecond);


        when(studentRepository.findAll()).thenReturn(studentEntities);

        //When
        Set<Student> students = studentService.findAllStudents();

        //Then
        assertEquals(1,students.size());
        verify(studentRepository,times(1)).findAll();


    }

    @Test
    public void findById() throws Exception {

        //Given
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(ID_1);

        when(studentRepository.findById(ID_1)).thenReturn(studentEntity);

        //When
        Student student = studentService.findById(ID_1);

        //Then
        assertEquals(ID_1,student.getId());
        verify(studentRepository,times(2)).findById(ID_1);
    }

    @Test
    public void findByFirstNameAndLastName() throws Exception {
        //Given
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(ID_1);
        studentEntity.setFirstName(STUDENT1_FIRST_NAME);
        studentEntity.setLastName(STUDENT1_LAST_NAME);

        when(studentRepository.findByFirstNameAndLastName(STUDENT1_FIRST_NAME,STUDENT1_LAST_NAME)).thenReturn(studentEntity);

        //When
        Student student = studentService.findByFirstNameAndLastName(STUDENT1_FIRST_NAME,STUDENT1_LAST_NAME);

        //Then
        assertEquals(ID_1, studentEntity.getId());
        assertEquals(STUDENT1_FIRST_NAME, studentEntity.getFirstName());
        assertEquals(STUDENT1_LAST_NAME,studentEntity.getLastName());
        verify(studentRepository,times(2)).findByFirstNameAndLastName(STUDENT1_FIRST_NAME,STUDENT1_LAST_NAME);
    }

    @Test
    public void save() throws Exception {

        //Given
        Student student = new Student();
        student.setId(ID_1);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(ID_1);

        //When
        studentService.save(student);

        //Then
        verify(studentRepository,times(1)).save(studentEntity);
    }

    @Test
    public void delete() throws Exception {
        long id = ID_1;

        studentService.delete(id);

        verify(studentRepository, times(2)).deleteById(id);
    }

    @Test
    public void addSubject() throws Exception{
        //Given
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(ID_1);

        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(ID_1);

        StudentSubjectEntity studentSubjectEntity = new StudentSubjectEntity();
        studentSubjectEntity.setSubjectEntity(subjectEntity);
        studentSubjectEntity.setStudentEntity(studentEntity);

        when(studentRepository.findById(ID_1)).thenReturn(studentEntity);
        when(subjectRepository.findById(ID_1)).thenReturn(subjectEntity);
        //When
        studentService.addSubjectToStudent(subjectEntity.getId(),studentEntity.getId());

        //Then
        verify(studentsSubjectRepository, times(1)).save(any());
    }

    @Test
    public void isStudentExist() throws Exception {
    }

}