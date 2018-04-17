package students.presence.list.mapper;

import org.junit.Before;
import org.junit.Test;
import students.presence.list.dto.StudentDTO;
import students.presence.list.model.Student;

import static org.junit.Assert.*;

public class StudentMapperTest {

    public static final String STUDENT_DTO_FIRST_NAME = "Jan";
    public static final String STUDENT_DTO_LAST_NAME = "Kowalski";
    public static final String STUDENT_DTO_EMAIL = "example@gmail.com";

    public static final int STUDENT_ID = 2;
    public static final String STUDENT_FIRST_NAME = "Adrian";
    public static final String STUDENT_LAST_NAME = "Nowak";
    public static final String STUDENT_EMAIL = "example2@gmail.com";

    private StudentMapper studentMapper;

    private StudentDTO studentDTO;

    private Student student;

    @Before
    public void setUp(){
        studentMapper = new StudentMapper();

        studentDTO = new StudentDTO();
        studentDTO.setFirstName(STUDENT_DTO_FIRST_NAME);
        studentDTO.setLastName(STUDENT_DTO_LAST_NAME);
        studentDTO.setEmail(STUDENT_DTO_EMAIL);

        student = new Student();
        student.setId(STUDENT_ID);
        student.setFirstName(STUDENT_FIRST_NAME);
        student.setLastName(STUDENT_LAST_NAME);
        student.setEmail(STUDENT_EMAIL);
    }

    @Test
    public void mapStudentDTOToStudent() throws Exception {
        Student mappedStudent = studentMapper.mapStudentDTOToStudent(studentDTO);

        assertEquals(mappedStudent.getId(), 0);
        assertEquals(mappedStudent.getFirstName(), studentDTO.getFirstName());
        assertEquals(mappedStudent.getLastName(), studentDTO.getLastName());
        assertEquals(mappedStudent.getEmail(), studentDTO.getEmail());
    }

    @Test
    public void mapStudentToStudentDTO() throws Exception {
        StudentDTO mappedStudentDTO = mappedStudentDTO = studentMapper.mapStudentToStudentDTO(student);

        assertEquals(mappedStudentDTO.getId(), student.getId());
        assertEquals(mappedStudentDTO.getFirstName(), student.getFirstName());
        assertEquals(mappedStudentDTO.getLastName(), student.getLastName());
        assertEquals(mappedStudentDTO.getEmail(), student.getEmail());

    }

}