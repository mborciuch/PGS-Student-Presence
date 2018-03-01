package students.presence.list.mapper;

import students.presence.list.dto.SubjectDTO;
import students.presence.list.model.Subject;
import org.springframework.stereotype.Component;


@Component
public class SubjectMapper {


    public Subject mapSubjectDTOToSubject(SubjectDTO subjectDTO){
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        subject.setLecturer(subjectDTO.getLecturer());
        return subject;

    }

    public SubjectDTO mapSubjectToSubjectDTO(Subject subject){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setName(subject.getName());
        subjectDTO.setLecturer(subject.getLecturer());
        return subjectDTO;
    }
}
