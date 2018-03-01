package students.presence.list.mapper;

import students.presence.list.dto.StudentDTO;
import students.presence.list.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student mapStudentDTOToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        return student;
    }

    public StudentDTO mapStudentToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        return studentDTO;
    }

}
