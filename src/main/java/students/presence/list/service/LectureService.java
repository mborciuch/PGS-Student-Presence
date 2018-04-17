package students.presence.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import students.presence.list.dto.LectureDTO;
import students.presence.list.model.Lecture;
import students.presence.list.repositories.LectureRepository;

import java.util.Date;

public class LectureService {

    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void saveLecture(LectureDTO lectureDTO){
        Lecture lecture = new Lecture();

    }
}
