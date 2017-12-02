package PGS.JAVADEV.PGS.Student.Presence.List.service;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.StudentSubject;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentSubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.StudentRepository;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.StudentsSubjectRepository;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentsSubjectRepository studentsSubjectRepository;


    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, StudentsSubjectRepository studentSubjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.studentsSubjectRepository = studentSubjectRepository;
    }

    @Transactional
    public Set<Student> findAllStudents(){
        Iterable<StudentEntity> studentEntities = studentRepository.findAll();
        Set<Student> students = new HashSet<>();
        studentEntities.forEach(student -> students.add(mapStudentToStudentEntity(student)));
        return students;
    }
    @Transactional
    public Student findById(long id){
        Student student = new Student();
       StudentEntity studentEntity = studentRepository.findById(id);
        if(studentEntity == null) {
            return null;
        }
        student = mapStudentToStudentEntity(studentRepository.findById(id));
        student.setSubjects(getAllSubjects(studentEntity));
        return student;
    }
    public Student findByFirstNameAndLastName(String firstName, String lastName){
        Student student = new Student();
        StudentEntity studentEntity = studentRepository.findByFirstNameAndLastName(firstName,lastName );
        if(studentEntity == null) {
            return null;
        }
        student = mapStudentToStudentEntity(studentRepository.findByFirstNameAndLastName(firstName,lastName ));
        student.setSubjects(getAllSubjects(studentEntity));
        return student;
    }
     /*   public StudentSubject getSubjects(long studentId){

    }*/

    public void save(Student student){
        studentRepository.save(mapStudentToStudentEntity(student));
    }
    public void delete(Long id){
        studentRepository.findById(id);
    }
    public boolean isStudentExist(Student student){
        StudentEntity studentEntity = studentRepository.findByFirstNameAndLastName(student.getFirstName(), student.getLastName());
     return studentEntity != null;
    }

    public void addSubjectToStudent(long studentId, long subjectId){

        StudentEntity studentEntity = studentRepository.findById(studentId);
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId);

        StudentSubjectEntity studentSubjectEntity = new StudentSubjectEntity();

        studentSubjectEntity.setStudentEntity(studentEntity);
        studentSubjectEntity.setSubjectEntity(subjectEntity);

        studentEntity.getStudentSubjectEntities().add(studentSubjectEntity);

        studentsSubjectRepository.save(studentSubjectEntity);
    }



    private Student mapStudentToStudentEntity(StudentEntity studentEntity){
        Student student = new Student();

        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setId(studentEntity.getId());

        return  student;
    }
    private Set<StudentSubject> getAllSubjects(StudentEntity studentEntity){
    Set<StudentSubject> studentSubjects = new HashSet<>();

            studentEntity.getStudentSubjectEntities().forEach(el -> {
                StudentSubject studentSubject = new StudentSubject();
                studentSubject.setGrade(el.getGradeEnum());
                studentSubject.setSubjectId(el.getSubjectEntity().getId());
                studentSubject.setSubjectName(el.getSubjectEntity().getName());
                studentSubjects.add(studentSubject);
        });
            return  studentSubjects;

    }


    private StudentEntity mapStudentToStudentEntity(Student student){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        return  studentEntity;

    }


}
