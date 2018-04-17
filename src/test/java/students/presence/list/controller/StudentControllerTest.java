package students.presence.list.controller;


import students.presence.list.dto.StudentDTO;
import students.presence.list.dto.CourseDTO;
import students.presence.list.model.Lecturer;
import students.presence.list.service.StudentService;
import students.presence.list.service.CourseService;
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

import static junit.framework.TestCase.fail;
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
    public static final String FIRST_NAME_LECTURER = "Jan";
    public static final String LAST_NAME_LECTURER = "Kowalski";

    @Mock
    StudentService studentService;

    @Mock
    CourseService courseService;

    @InjectMocks
    StudentController studentController;

    MockMvc mockMvc;

    ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    Lecturer lecturer;

    StudentDTO studentDTOFirst;

    StudentDTO studentDTOSecond;

    Set<StudentDTO> studentDTOS = new HashSet<>();

    @Before
    public void setUp() throws Exception {
   /*     mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .build();

        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName(FIRST_NAME_LECTURER);
        lecturer.setLastName(LAST_NAME_LECTURER);

        studentDTOFirst= new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setEnrollmentDTOS(new HashSet<>());

        studentDTOSecond = new StudentDTO();
        studentDTOSecond.setId(ID_2);
        studentDTOSecond.setFirstName(STUDENT2_FIRST_NAME);
        studentDTOSecond.setLastName(STUDENT2_LAST_NAME);
        studentDTOSecond.setEnrollmentDTOS(new HashSet<>());

        Set<StudentDTO> studentDTOS = new HashSet<>();
        studentDTOS.add(studentDTOFirst);
        studentDTOS.add(studentDTOSecond);
    }

    @Test
    public void getAllStudents() throws Exception {
        //Given
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
        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);

        //When //Then
        mockMvc.perform(get(StudentController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentByFirstNameAndLastName() throws Exception {
        //Given
        Set<StudentDTO> studentsByName = new HashSet<>();
        studentsByName.add(studentDTOFirst);
        when(studentService.findByFirstNameAndLastName(STUDENT1_FIRST_NAME, STUDENT1_LAST_NAME)).thenReturn(studentsByName);

        //when //Then
        mockMvc.perform(get(StudentController.BASE_URL  + "/" + STUDENT1_FIRST_NAME + "/" + STUDENT1_LAST_NAME)
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
        //when //Then
        mockMvc.perform(post(StudentController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(studentDTOFirst)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateStudent() throws Exception {
        fail();
    }

    @Test
    public void addSubjectToStudent() throws Exception {
        //Given
        CourseDTO courseDTOFirst = new CourseDTO();
        courseDTOFirst.setId(ID_1);
        courseDTOFirst.setName(FIRST_SUBJECT);
        courseDTOFirst.setLecturer(lecturer);
        courseDTOFirst.setStudentDTOS(new HashSet<>());


        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);
        when(courseService.findById(ID_1)).thenReturn(courseDTOFirst);

        //When//Then
        mockMvc.perform(post(StudentController.BASE_URL + "/" + ID_1 + "/add" + "/" + ID_1))
                .andExpect(status().isOk());*/
    }


}