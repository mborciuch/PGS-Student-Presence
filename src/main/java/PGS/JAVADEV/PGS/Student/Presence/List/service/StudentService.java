package PGS.JAVADEV.PGS.Student.Presence.List.service;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.StudentSubjectGrade;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentSubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectService subjectService;

    @Autowired
    GradeService gradeService;


    public List<Student> findAllStudents(){
        Iterable<StudentEntity> studentEntities = studentRepository.findAll();
        List<Student> students = new ArrayList<>();
        studentEntities.forEach(student -> students.add(mapStudentToStudentEntity(student)));
        return students;
    }

    public Student findById(long id){
       StudentEntity studentEntity = studentRepository.findById(id);
        if(studentEntity == null) {
            return null;
        }
        return mapStudentToStudentEntity(studentRepository.findById(id));
    }
    public void save(Student student){
        studentRepository.save(mapStudentToStudentEntity(student));
    }
    public void delete(long id){
        studentRepository.deleteById(id);
    }
    public boolean isStudentExist(Student student){
        StudentEntity studentEntity = studentRepository.findByFirstNameAndLastName(student.getFirstName(), student.getLastName());
     return studentEntity != null;
    }

    public List<Subject> getAllStudents(Student student){
      StudentEntity studentEntity = studentRepository.findById(student.getId());
      Set<StudentSubjectEntity> studentSubjectEntities = studentEntity.getStudentSubjectEntities();
      List<Subject> subjects = new ArrayList<>();
      studentSubjectEntities.forEach(subject -> subjects.add(subjectService.map((subject.getSubjectEntity()))));
      return subjects;
    }



    public Student mapStudentToStudentEntity(StudentEntity studentEntity){
        Student student = new Student();
        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setId(studentEntity.getId());
        return  student;
    }

    public StudentEntity mapStudentToStudentEntity(Student student){
        StudentEntity studentEntity = new StudentEntity();
        student.setFirstName(student.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setId(studentEntity.getId());
        return  studentEntity;

    }
    public StudentSubjectGrade mapStudentSubjectEntityToStudentSubjectGrade(StudentSubjectEntity studentSubjectEntity){
        StudentSubjectGrade studentSubjectGrade = new StudentSubjectGrade();
        studentSubjectGrade.setStudent(mapStudentToStudentEntity(studentSubjectEntity.getStudentEntity()));
        studentSubjectGrade.setSubject(subjectService.map(studentSubjectEntity.getSubjectEntity()));
        studentSubjectGrade.setGrade(gradeService.map(studentSubjectEntity.getGradeEntity()));

        return studentSubjectGrade;
    }

}
