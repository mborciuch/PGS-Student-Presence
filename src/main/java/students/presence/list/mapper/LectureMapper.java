package students.presence.list.mapper;

import org.springframework.stereotype.Component;
import students.presence.list.dto.LectureDTO;
import students.presence.list.model.Lecture;

@Component
public class LectureMapper {
    public Lecture mapLectureDTOtoLecture(LectureDTO lectureDTO){
        Lecture lecture = new Lecture();
        lecture.setDate(lectureDTO.getDate());
        lecture.setName(lectureDTO.getName());

        return lecture;
    }

    public LectureDTO mapLectureToLectureDTO(Lecture lecture){
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setName(lecture.getName());
        lectureDTO.setDate(lecture.getDate());
        lectureDTO.setCourseName(lecture.getCourse().getName());
        return lectureDTO;
    }
}
