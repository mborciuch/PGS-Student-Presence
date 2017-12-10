package PGS.JAVADEV.PGS.Student.Presence.List.mapper;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student mapStudentEntityToStudent(StudentEntity studentEntity){
        Student student = new Student();
        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        return  student;
    }
    public StudentEntity mapStudentToStudentEntity(Student student){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        return  studentEntity;
    }
}
