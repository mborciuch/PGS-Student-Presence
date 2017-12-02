package PGS.JAVADEV.PGS.Student.Presence.List.service;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Grade;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEnum;
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
public class SubjectService {


    private final SubjectRepository subjectRepository;

    private final  StudentRepository studentRepository;

   private final StudentsSubjectRepository studentSubjectRepository;


    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository, StudentsSubjectRepository studentSubjectRepository) {
        this.subjectRepository = subjectRepository;
       this.studentRepository = studentRepository;
       this.studentSubjectRepository = studentSubjectRepository;
    }

    @Transactional
    public Set<Subject> findAllSubjects(){
        Iterable<SubjectEntity> subjectEntities = subjectRepository.findAll();
        Set<Subject> subjects = new HashSet<>();
        subjectEntities.forEach(subject -> subjects.add(mapSubjectEntityToSubject(subject)));
        return subjects;
    }


    @Transactional
    public Subject findById(long id){
        Subject subject = new Subject();
        SubjectEntity subjectEntity = subjectRepository.findById(id);
        if(subjectEntity == null) {
            return null;
        }
        subject = mapSubjectEntityToSubject(subjectRepository.findById(id));
        subject.setStudents(getAllStudents(subjectEntity));
        return subject;
    }

    @Transactional
    public Subject findByName(String name){
        Subject subject = new Subject();
        SubjectEntity subjectEntity = subjectRepository.findByName(name);
        if(subjectEntity == null) {
            return null;
        }
        subject = mapSubjectEntityToSubject(subjectRepository.findByName(name));
        subject.setStudents(getAllStudents(subjectEntity));
        return subject;
    }


    public void save(Subject subject){
        subjectRepository.save(mapSubjectToSubjectEntity(subject));
    }

    public void delete(long id){
        subjectRepository.deleteById(id);
    }

    @Transactional
    public void addStudentToSubject(long studentId, long subjectId){
        StudentSubjectEntity studentSubjectEntity = new StudentSubjectEntity();
        studentSubjectEntity.setStudentEntity(studentRepository.findById(studentId));
        studentSubjectEntity.setSubjectEntity(subjectRepository.findById(subjectId));
       studentSubjectRepository.save(studentSubjectEntity);
    }


    @Transactional
    public void addGradeToStudent(long studentId, long subjectId, GradeEnum grade){
        StudentSubjectEntity studentSubjectEntity = studentSubjectRepository.findAllByStudentEntityIdAndSubjectEntityId(studentId, subjectId);
        studentSubjectEntity.setGradeEnum(grade);
        studentSubjectRepository.save(studentSubjectEntity);
    }




    public boolean isSubjectExist(Subject subject){
        SubjectEntity subjectEntity = subjectRepository.findByName(subject.getName());
       return  subjectEntity != null;
    }

   private SubjectEntity mapSubjectToSubjectEntity(Subject subject){
        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setName(subject.getName());
        subjectEntity.setLecturer(subject.getLecturer());
        return subjectEntity;

    }

    private Subject mapSubjectEntityToSubject(SubjectEntity subjectEntity){
        Subject subject = new Subject();
        subject.setId(subjectEntity.getId());
        subject.setName(subjectEntity.getName());
        subject.setLecturer(subjectEntity.getLecturer());
        return  subject;
    }

    private Set<Student> getAllStudents(SubjectEntity subjectEntity){
        Set<Student> students = new HashSet<>();

        subjectEntity.getStudentSubjectEntities().forEach(el -> {
            Student student = new Student();
            student.setFirstName(el.getStudentEntity().getFirstName());
            student.setLastName(el.getStudentEntity().getLastName());
            students.add(student);
        });
        return  students;

    }

}
