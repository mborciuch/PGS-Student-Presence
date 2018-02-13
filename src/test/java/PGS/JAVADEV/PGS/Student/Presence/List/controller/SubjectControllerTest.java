package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEnum;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.StudentRepository;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.SubjectRepository;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import PGS.JAVADEV.PGS.Student.Presence.List.service.SubjectService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SubjectControllerTest {

    public static final String FIRST_SUBJECT = "Matematyka";
    public static final String FIRST_LECTURER = "Kowalski";
    public static final String SECOND_SUBJECT = "Fizyka";
    public static final String SECON_LECTURER = "Nowak";
    public static final long ID_1 = 1L;
    public static final long ID_2 = 2l;
    public static final String STUDENT1_FIRST_NAME = "Jan";
    public static final String STUDENT1_LAST_NAME = "Kowalski";

    @Mock
    SubjectRepository subjectRepository;

    @Mock
    StudentRepository studentRepository;


    @Mock
    SubjectService subjectService;

    @Mock
    StudentService studentService;


    @InjectMocks
    SubjectController subjectController;


    MockMvc mockMvc;

    ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(subjectController)
                .build();

        ArgumentCaptor<Set<Subject>> argumentCaptorSet = ArgumentCaptor.forClass(Set.class);
    }

    @Test
    public void getAllSubjects() throws Exception {
        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());

        Subject subjectSecond = new Subject();
        subjectSecond.setId(ID_2);
        subjectSecond.setName(SECOND_SUBJECT);
        subjectSecond.setLecturer(SECON_LECTURER);
        subjectSecond.setStudents(new HashSet<>());

        Set<Subject> subjects = new HashSet<>();
        subjects.add(subjectFirst);
        subjects.add(subjectSecond);


        when(subjectService.findAllSubjects()).thenReturn(subjects);

        //when //Then
        mockMvc.perform(get(SubjectController.BASE_URL))
                .andDo(print())
                .andExpect(status().isOk());




    }

    @Test
    public void getSubjectById() throws Exception {
        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());

        when(subjectService.findById(ID_1)).thenReturn(subjectFirst);

        //when //Then
        mockMvc.perform(get(SubjectController.BASE_URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getSubjectByName() throws Exception {
        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());

        when(subjectService.findByName(FIRST_SUBJECT)).thenReturn(subjectFirst);


        //when //Then
        mockMvc.perform(get(SubjectController.BASE_URL + "/byName/Matematyka"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSubject() throws Exception {


        mockMvc.perform(delete(SubjectController.BASE_URL + "/1"))

                .andExpect(status().isNoContent());

    }

    @Test
    public void createSubject() throws Exception {
        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());

        //when //Then
       mockMvc.perform(post(SubjectController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(subjectFirst)))
                .andExpect(status().isCreated());


    }

    @Test
    public void updateSubject() throws Exception {

        //Given
        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());

        Subject subjectSecond = new Subject();
        subjectSecond.setId(ID_2);
        subjectSecond.setName(SECOND_SUBJECT);
        subjectSecond.setLecturer(SECON_LECTURER);
        subjectSecond.setStudents(new HashSet<>());

        when(subjectService.findById(ID_1)).thenReturn(subjectFirst);

        //when //Then
        mockMvc.perform(put(SubjectController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(subjectSecond)))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudentToSubject() throws Exception {

        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());

        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());


        when(studentService.findById(ID_1)).thenReturn(studentFirst);
        when(subjectService.findById(ID_1)).thenReturn(subjectFirst);


        mockMvc.perform(post(SubjectController.BASE_URL + "/" + ID_1 + "/" + ID_1 ))
                .andExpect(status().isOk());





    }

    @Test
    public void addGrade() throws Exception {

        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());

        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());

        GradeEnum gradeEnum = GradeEnum.A;


        when(studentService.findById(ID_1)).thenReturn(studentFirst);
        when(subjectService.findById(ID_1)).thenReturn(subjectFirst);

        mockMvc.perform(post(SubjectController.BASE_URL + "/1/1/addGrade" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(gradeEnum)))
                .andExpect(status().isOk());

    }

}