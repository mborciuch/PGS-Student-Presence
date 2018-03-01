package students.presence.list.controller;


import students.presence.list.dto.StudentDTO;
import students.presence.list.dto.SubjectDTO;
import students.presence.list.service.StudentService;
import students.presence.list.service.SubjectService;
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

@RunWith(MockitoJUnitRunner.Silent.class)
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
        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());


        StudentDTO studentDTOSecond = new StudentDTO();
        studentDTOSecond.setId(ID_2);
        studentDTOSecond.setFirstName(STUDENT2_FIRST_NAME);
        studentDTOSecond.setLastName(STUDENT2_LAST_NAME);
        studentDTOSecond.setSubjects(new HashSet<>());

        Set<StudentDTO> studentDTOS = new HashSet<>();
        studentDTOS.add(studentDTOFirst);
        studentDTOS.add(studentDTOSecond);

        when(studentService.findAllStudents()).thenReturn(studentDTOS);

        // When //Then
        mockMvc.perform(get(StudentController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentById() throws Exception {
        //Given
        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());

        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);

        //when //Then
        mockMvc.perform(get(StudentController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentByFirstNameAndLastName() throws Exception {
        //Given
        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());

        when(studentService.findByFirstNameAndLastName(STUDENT1_FIRST_NAME, STUDENT1_LAST_NAME)).thenReturn(studentDTOFirst);

        //when //Then
        mockMvc.perform(get(StudentController.BASE_URL + "/byName" + "/" + STUDENT1_FIRST_NAME + "/" + STUDENT1_LAST_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentById() throws Exception {
        mockMvc.perform(delete(StudentController.BASE_URL + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void createStudent() throws Exception {
        //Given
        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());

        //when //Then
        mockMvc.perform(post(StudentController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(studentDTOFirst)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateStudent() throws Exception {
        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());


        StudentDTO studentDTOSecond = new StudentDTO();
        studentDTOSecond.setId(ID_2);
        studentDTOSecond.setFirstName(STUDENT2_FIRST_NAME);
        studentDTOSecond.setLastName(STUDENT2_LAST_NAME);
        studentDTOSecond.setSubjects(new HashSet<>());
    }

    @Test
    public void addSubjectToStudent() throws Exception {
        //Given
        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());


        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(FIRST_LECTURER);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());


        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);
        when(subjectService.findById(ID_1)).thenReturn(subjectDTOFirst);

        //When//Then
        mockMvc.perform(post(StudentController.BASE_URL + "/" + ID_1 + "/add" + "/" + ID_1))
                .andExpect(status().isOk());
    }


}