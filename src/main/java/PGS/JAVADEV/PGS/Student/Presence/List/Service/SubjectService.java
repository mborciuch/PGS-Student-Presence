package PGS.JAVADEV.PGS.Student.Presence.List.Service;

import PGS.JAVADEV.PGS.Student.Presence.List.DTO.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.Model.SubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> findAllSubjects(){
        Iterable<SubjectEntity> subjectEntities = subjectRepository.findAll();
        List<Subject> subjects = new ArrayList<>();
        subjectEntities.forEach(student -> subjects .add(map(student)));
        return subjects;
    }

    public Subject findById(long id){
        SubjectEntity subjectEntity = subjectRepository.findById(id);
        if(subjectEntity == null) {
            return null;
        }
        return map(subjectRepository.findById(id));
    }
    public Subject findByName(String name){
        SubjectEntity subjectEntity = subjectRepository.findByName(name);
        if(subjectEntity == null) {
            return null;
        }
        return map(subjectRepository.findByName(name));
    }


    public void save(Subject subject){
        subjectRepository.save(map(subject));
    }
    public void delete(long id){
        subjectRepository.deleteById(id);
    }

    public boolean isSubjectExist(Subject subject){
        SubjectEntity subjectEntity = subjectRepository.findByName(subject.getName());
        if(subject != null){
            return true;
        }
        return false;
    }

    public SubjectEntity map (Subject subject){
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(subject.getId());
        subjectEntity.setName(subject.getName());
        subjectEntity.setLecturer(subject.getLecturer());
        return subjectEntity;

    }

    public Subject map(SubjectEntity subjectEntity){
        Subject subject = new Subject();
        subject.setId(subjectEntity.getId());
        subject.setName(subject.getName());
        subject.setLecturer(subjectEntity.getLecturer());
        return  subject;
    }

}
