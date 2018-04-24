package students.presence.list.controller;

import students.presence.list.dto.CourseDTO;
import students.presence.list.dto.StudentDTO;
import students.presence.list.model.Lecturer;
import students.presence.list.repositories.StudentRepository;
import students.presence.list.repositories.CourseRepository;
import students.presence.list.service.StudentService;
import students.presence.list.service.CourseService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CourseControllerTest {

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

    Lecturer firstLecturer;

    Lecturer secondLecturer;

    CourseDTO courseDTOFirst;

    CourseDTO courseDTOSecond;

    StudentDTO studentDTOFirst;

    Set<CourseDTO> courseDTOS;

    @Mock
    CourseRepository courseRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    CourseService courseService;

    @Mock
    StudentService studentService;


    @InjectMocks
    CourseController courseController;


    MockMvc mockMvc;

    ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Before
    public void setUp() throws Exception {

 /*       mockMvc = MockMvcBuilders.standaloneSetup(subjectController)
                .build();
        ArgumentCaptor<Set<CourseDTO>> argumentCaptorSet = ArgumentCaptor.forClass(Set.class);

        courseDTOFirst = new CourseDTO();
        courseDTOFirst.setId(ID_1);
        courseDTOFirst.setName(FIRST_SUBJECT);
        courseDTOFirst.setLecturer(firstLecturer);
        courseDTOFirst.setStudentDTOS(new HashSet<>());

        courseDTOSecond = new CourseDTO();
        courseDTOSecond.setId(ID_2);
        courseDTOSecond.setName(SECOND_SUBJECT);
        courseDTOSecond.setLecturer(secondLecturer);
        courseDTOSecond.setStudentDTOS(new HashSet<>());


        firstLecturer = new Lecturer();
        firstLecturer.setFirstName(FIRST_NAME_FIRST_LECTURER);
        firstLecturer.setLastName(LAST_NAME_FIRST_LECTURER);

        secondLecturer = new Lecturer();
        secondLecturer.setFirstName(FIRST_NAME_SECOND_LECTURER);
        secondLecturer.setLastName(LAST_NAME_SECOND_LECTURER);

        Set<CourseDTO> courseDTOS = new HashSet<>();
        courseDTOS.add(courseDTOFirst);
        courseDTOS.add(courseDTOSecond);


        studentDTOFirst = new StudentDTO();
        studentDTOFirst.setId(ID_1);
        studentDTOFirst.setFirstName(STUDENT1_FIRST_NAME);
        studentDTOFirst.setLastName(STUDENT1_LAST_NAME);
        studentDTOFirst.setEnrollmentDTOS(new HashSet<>());

    }

    @Test
    public void getAllCourses() throws Exception {

        //Given
        when(courseService.findAllSubjects()).thenReturn(courseDTOS);

        //When //Then
        mockMvc.perform(get(SubjectController.BASE_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCourseById() throws Exception {
        //Given
        when(courseService.findById(ID_1)).thenReturn(courseDTOFirst);

        //When //Then
        mockMvc.perform(get(SubjectController.BASE_URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCourseByName() throws Exception {
        //Given
        Set<CourseDTO> subjectByName = new HashSet<>();
        subjectByName.add(courseDTOFirst);
        when(courseService.findByName(FIRST_SUBJECT)).thenReturn(subjectByName);

        //when //Then
        mockMvc.perform(get(SubjectController.BASE_URL + "/byName/Matematyka"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCourse() throws Exception {
        mockMvc.perform(delete(SubjectController.BASE_URL + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void createSubject() throws Exception {
        //when //Then
        mockMvc.perform(post(SubjectController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(courseDTOFirst)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateSubject() throws Exception {
        //Given
        when(courseService.findById(ID_1)).thenReturn(courseDTOFirst);

        //When //Then
        mockMvc.perform(put(SubjectController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(courseDTOSecond)))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudentToSubject() throws Exception {
        //Given
        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);
        when(courseService.findById(ID_1)).thenReturn(courseDTOFirst);

        //When //Then
        mockMvc.perform(post(SubjectController.BASE_URL + "/" + ID_1 + "/" + ID_1))
                .andExpect(status().isOk());
    }

    @Test
    public void addGrade() throws Exception {

        //Given
        Grade grade = Grade.A;


        when(studentService.findById(ID_1)).thenReturn(studentDTOFirst);
        when(courseService.findById(ID_1)).thenReturn(courseDTOFirst);

        //When //Then
        mockMvc.perform(post(SubjectController.BASE_URL + "/1/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(grade)))
                .andExpect(status().isOk());
                */
    }

}