package students.presence.list.mapper;

import org.springframework.stereotype.Component;
import students.presence.list.dto.LectureDTO;
import students.presence.list.model.Lecture;

@Component
public class LectureMapper {
    public Lecture LectureDTOtoLecture(LectureDTO lectureDTO){
        Lecture lecture = new Lecture();
        lecture.setDate(lectureDTO.getDate());
        lecture.setName(lectureDTO.getName());

        return lecture;
    }

    public LectureDTO LectureToLectureDTO(Lecture lecture){
        LectureDTO lectureDTO = new LectureDTO();
        return lectureDTO;
    }
}
