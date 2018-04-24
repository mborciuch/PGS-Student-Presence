package students.presence.list.mapper;

import org.springframework.stereotype.Component;
import students.presence.list.dto.LecturerDTO;
import students.presence.list.model.Lecturer;

@Component
public class LecturerMapper {

    public Lecturer mapLecturerDTOtoLecturer(LecturerDTO lecturerDTO){
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName(lecturerDTO.getFirstName());
        lecturer.setLastName(lecturerDTO.getLastName());
        lecturer.setEmail(lecturerDTO.getEmail());
        return lecturer;
    }

    public LecturerDTO mapLecturerToLecturerDTO(Lecturer lecturer){
        LecturerDTO lecturerDTO = new LecturerDTO();
        lecturerDTO.setId(lecturer.getId());
        lecturerDTO.setFirstName(lecturer.getFirstName());
        lecturerDTO.setLastName(lecturer.getLastName());
        lecturerDTO.setEmail(lecturer.getEmail());
        return lecturerDTO;
    }
}
