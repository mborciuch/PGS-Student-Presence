package students.presence.list.controller;

import students.presence.list.dto.StudentDTO;
import students.presence.list.dto.SubjectDTO;
import students.presence.list.model.GradeEnum;
import students.presence.list.model.Lecturer;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.SubjectRepository;
import students.presence.list.service.StudentService;
import students.presence.list.service.SubjectService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SubjectControllerTest {

    public static final String FIRST_SUBJECT = "Matematyka";

    public static final String FIRST_NAME_FIRST_LECTURER = "Andrzej";

    public static final String LAST_NAME_FIRST_LECTURER = "Kowalski";

    public static final String SECOND_SUBJECT = "Fizyka";

    public static final String FIRST_NAME_SECOND_LECTURER = "Marcin";

    public static final String LAST_NAME_SECOND_LECTURER = "Nowak";

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

    Lecturer firstLecturer;

    Lecturer secondLecturer;


    MockMvc mockMvc;

    ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(subjectController)
                .build();
        ArgumentCaptor<Set<SubjectDTO>> argumentCaptorSet = ArgumentCaptor.forClass(Set.class);

        firstLecturer = new Lecturer();
        firstLecturer.setFirstName(FIRST_NAME_FIRST_LECTURER);
        firstLecturer.setLastName(LAST_NAME_FIRST_LECTURER);

        secondLecturer = new Lecturer();
        secondLecturer.setFirstName(FIRST_NAME_SECOND_LECTURER);
        secondLecturer.setLastName(LAST_NAME_SECOND_LECTURER);
    }

    @Test
    public void getAllSubjects() throws Exception {
        //Given
        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(firstLecturer);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());

        SubjectDTO subjectDTOSecond = new SubjectDTO();
        subjectDTOSecond.setId(ID_2);
        subjectDTOSecond.setName(SECOND_SUBJECT);
        subjectDTOSecond.setLecturer(secondLecturer);
        subjectDTOSecond.setStudentDTOS(new HashSet<>());

        Set<SubjectDTO> subjectDTOS = new HashSet<>();
        subjectDTOS.add(subjectDTOFirst);
        subjectDTOS.add(subjectDTOSecond);


        when(subjectService.findAllSubjects()).thenReturn(subjectDTOS);

        //when //Then
        mockMvc.perform(get(SubjectController.BASE_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getSubjectById() throws Exception {
        //Given
        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(firstLecturer);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());

        when(subjectService.findById(ID_1)).thenReturn(subjectDTOFirst);

        //when //Then
        mockMvc.perform(get(SubjectController.BASE_URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getSubjectByName() throws Exception {
        //Given
        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(secondLecturer);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());

        when(subjectService.findByName(FIRST_SUBJECT)).thenReturn(subjectDTOFirst);


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
        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(firstLecturer);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());

        //when //Then
        mockMvc.perform(post(SubjectController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(subjectDTOFirst)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateSubject() throws Exception {

        //Given
        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(firstLecturer);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());

        SubjectDTO subjectDTOSecond = new SubjectDTO();
        subjectDTOSecond.setId(ID_2);
        subjectDTOSecond.setName(SECOND_SUBJECT);
        subjectDTOSecond.setLecturer(secondLecturer);
        subjectDTOSecond.setStudentDTOS(new HashSet<>());

        when(subjectService.findById(ID_1)).thenReturn(subjectDTOFirst);

        //When //Then
        mockMvc.perform(put(SubjectController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(subjectDTOSecond)))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudentToSubject() throws Exception {

        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(firstLecturer);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());

        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());


        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);
        when(subjectService.findById(ID_1)).thenReturn(subjectDTOFirst);


        mockMvc.perform(post(SubjectController.BASE_URL + "/" + ID_1 + "/" + ID_1))
                .andExpect(status().isOk());
    }

    @Test
    public void addGrade() throws Exception {

        //Given
        SubjectDTO subjectDTOFirst = new SubjectDTO();
        subjectDTOFirst.setId(ID_1);
        subjectDTOFirst.setName(FIRST_SUBJECT);
        subjectDTOFirst.setLecturer(firstLecturer);
        subjectDTOFirst.setStudentDTOS(new HashSet<>());

        StudentDTO studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setSubjects(new HashSet<>());

        GradeEnum gradeEnum = GradeEnum.A;


        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);
        when(subjectService.findById(ID_1)).thenReturn(subjectDTOFirst);

        //When //Then
        mockMvc.perform(post(SubjectController.BASE_URL + "/1/1/addGrade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(gradeEnum)))
                .andExpect(status().isOk());
    }

}