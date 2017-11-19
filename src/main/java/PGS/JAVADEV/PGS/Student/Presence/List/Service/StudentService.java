package PGS.JAVADEV.PGS.Student.Presence.List.Service;

import PGS.JAVADEV.PGS.Student.Presence.List.DTO.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.Model.StudentEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAllStudents(){
        Iterable<StudentEntity> studentEntities = studentRepository.findAll();
        List<Student> students = new ArrayList<>();
        studentEntities.forEach(student -> students.add(map(student)));
        return students;
    }

    public Student findById(long id){
       StudentEntity studentEntity = studentRepository.findById(id);
        if(studentEntity == null) {
            return null;
        }
        return map(studentRepository.findById(id));
    }
    public void save(Student student){
        studentRepository.save(map(student));
    }
    public void delete(StudentEntity studentEntity){
        studentRepository.delete(studentEntity);
    }


    public Student map( StudentEntity studentEntity){
        Student student = new Student();
        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setId(studentEntity.getId());
        return  student;
    }

    public StudentEntity map(Student student){
        StudentEntity studentEntity = new StudentEntity();
        student.setFirstName(student.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setId(studentEntity.getId());
        return  studentEntity;

    }

}
