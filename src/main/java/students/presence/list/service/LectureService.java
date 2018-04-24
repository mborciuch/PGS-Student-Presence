package students.presence.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import students.presence.list.dto.LectureDTO;
import students.presence.list.mapper.LectureMapper;
import students.presence.list.model.Course;
import students.presence.list.model.Lecture;
import students.presence.list.repositories.CourseRepository;
import students.presence.list.repositories.LectureRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LectureService {

    private final LectureRepository lectureRepository;

    private final CourseRepository courseRepository;

    private final LectureMapper lectureMapper;

    private final CourseService courseService;

    public LectureService (LectureRepository lectureRepository,CourseRepository courseRepository,
                          LectureMapper lectureMapper,
                          CourseService courseService) {
        this.lectureRepository = lectureRepository;
        this.lectureMapper = lectureMapper;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }


    public LectureDTO findById(long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture == null){
            return null;
        }
       LectureDTO lectureDTO = lectureMapper.mapLectureToLectureDTO(lecture);
        return lectureDTO;
    }

    public Set<LectureDTO> findByName (String lectureName){
        Iterable<Lecture> lectures = lectureRepository.findByName(lectureName);
        Set<LectureDTO> lectureDTOS = new HashSet<>();
        lectures.forEach(lecture -> lectureDTOS.add(lectureMapper.mapLectureToLectureDTO(lecture)));
        return lectureDTOS;
    }

    public void saveLecture(long courseId, LectureDTO lectureDTO){
         Course course = courseRepository.findById(courseId);
         course.getLectures().add(lectureRepository.save(lectureMapper.mapLectureDTOtoLecture(lectureDTO)));
         courseRepository.save(course);
    }

    public void deleteLecture(long LectureId){
        lectureRepository.deleteById(LectureId);
    }

}
