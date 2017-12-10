package PGS.JAVADEV.PGS.Student.Presence.List.mapper;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class SubjectMapper {


    public SubjectEntity mapSubjectToSubjectEntity(Subject subject){
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setName(subject.getName());
        subjectEntity.setLecturer(subject.getLecturer());
        return subjectEntity;

    }

    public Subject mapSubjectEntityToSubject(SubjectEntity subjectEntity){
        Subject subject = new Subject();
        subject.setName(subjectEntity.getName());
        subject.setLecturer(subjectEntity.getLecturer());
        return  subject;
    }
}
