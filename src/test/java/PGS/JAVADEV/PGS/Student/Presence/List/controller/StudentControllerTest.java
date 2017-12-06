package PGS.JAVADEV.PGS.Student.Presence.List.controller;


import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import PGS.JAVADEV.PGS.Student.Presence.List.service.SubjectService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    public static final long ID_1 = 1l;
    public static final String STUDENT1_FIRST_NAME = "Jan";
    public static final String STUDENT1_LAST_NAME = "Kowalski";
    public static final long ID_2 = 2l;
    public static final String STUDENT2_FIRST_NAME = "Adam";
    public static final String STUDENT2_LAST_NAME = "Nowak";
    public static final String FIRST_SUBJECT = "Matematyka";
    public static final String FIRST_LECTURER = "Kowalski";

    @Mock
    StudentService studentService;

    @Mock
    SubjectService subjectService;

    @InjectMocks
    StudentController studentController;

    MockMvc mockMvc;

    ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)

                .build();
    }

    @Test
    public void getAllStudents() throws Exception {
        //Given
        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());


        Student studentSecond = new Student();
        studentSecond.setId(ID_2);
        studentSecond.setFirstName(STUDENT2_FIRST_NAME);
        studentSecond.setLastName(STUDENT2_LAST_NAME);
        studentSecond.setSubjects(new HashSet<>());

        Set<Student> students = new HashSet<>();
        students.add(studentFirst);
        students.add(studentSecond);

        when(studentService.findAllStudents()).thenReturn(students);

        // When //Then
        mockMvc.perform(get(StudentController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentById() throws Exception {
        //Given
        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());

        when(studentService.findById(ID_1)).thenReturn(studentFirst);

        //when //Then
        mockMvc.perform(get(StudentController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentByFirstNameAndLastName() throws Exception {
        //Given
        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());

        when(studentService.findByFirstNameAndLastName(STUDENT1_FIRST_NAME,STUDENT1_LAST_NAME)).thenReturn(studentFirst);

        //when //Then

        mockMvc.perform(get(StudentController.BASE_URL + "/" + STUDENT1_FIRST_NAME + "/" + STUDENT1_LAST_NAME)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentById() throws Exception {
        mockMvc.perform(delete(StudentController.BASE_URL+ "/1") )
                .andExpect(status().isNoContent());
    }

    @Test
    public void createStudent() throws Exception {
        //Given
        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());

        //when //Then
        mockMvc.perform(post(StudentController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(studentFirst)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateStudent() throws Exception {
        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());


        Student studentSecond = new Student();
        studentSecond.setId(ID_2);
        studentSecond.setFirstName(STUDENT2_FIRST_NAME);
        studentSecond.setLastName(STUDENT2_LAST_NAME);
        studentSecond.setSubjects(new HashSet<>());
    }
    @Test
    public void addSubjectToStudent()throws Exception{
        //Given
        Student studentFirst = new Student();
        studentFirst.setId(ID_1);
        studentFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentFirst.setLastName(STUDENT1_LAST_NAME);
        studentFirst.setSubjects(new HashSet<>());


        Subject subjectFirst = new Subject();
        subjectFirst.setId(ID_1);
        subjectFirst.setName(FIRST_SUBJECT);
        subjectFirst.setLecturer(FIRST_LECTURER);
        subjectFirst.setStudents(new HashSet<>());



       when(studentService.findById(ID_1)).thenReturn(studentFirst);
        when(subjectService.findById(ID_1)).thenReturn(subjectFirst);

        //When//Then
      mockMvc.perform(post(StudentController.BASE_URL + "/" + ID_1 + "/add" +   "/" + ID_1))
              .andExpect(status().isOk());
    }


}