package students.presence.list.service;

import org.springframework.stereotype.Service;
import students.presence.list.dto.LecturerDTO;
import students.presence.list.mapper.LecturerMapper;
import students.presence.list.model.Lecturer;
import students.presence.list.repositories.LecturerRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    private final LecturerMapper lecturerMapper;

    public LecturerService(LecturerRepository lecturerRepository, LecturerMapper lecturerMapper) {
        this.lecturerRepository = lecturerRepository;
        this.lecturerMapper = lecturerMapper;
    }

    public LecturerDTO findById(long lecturerId){
        LecturerDTO lecturerDTO = new LecturerDTO();
        Lecturer lecturer = lecturerRepository.findById(lecturerId);
        if (lecturer == null) {
            return null;
        }
        lecturerDTO = lecturerMapper.mapLecturerToLecturerDTO(lecturer);
        Set<String> courses = lecturer.getCourse().stream()
                .map(el-> el.getName())
                .collect(Collectors.toSet());
        lecturerDTO.setCourseNames(courses);
        return lecturerDTO;
    }

    public Set<LecturerDTO> findAllLecturers(){
        Iterable<Lecturer> lecturers = lecturerRepository.findAll();
        Set<LecturerDTO> lecturerDTOS = new HashSet<>();
        lecturers.forEach(lecturer -> lecturerMapper.mapLecturerToLecturerDTO(lecturer));
        return lecturerDTOS;
    }

    public Set<LecturerDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        Set<LecturerDTO> lecturerDTOS = new HashSet<>();
        Iterable<Lecturer> studentEntities = lecturerRepository.findByFirstNameAndLastName(firstName, lastName);
        studentEntities.forEach(student -> lecturerMapper.mapLecturerToLecturerDTO(student));
        return lecturerDTOS;
    }

    public void saveLecturer(LecturerDTO lecturerDTO){
        lecturerRepository.save(lecturerMapper.mapLecturerDTOtoLecturer(lecturerDTO));
    }

    public void updateLecturer (long lecturerId, LecturerDTO lecturerDTO){
        lecturerDTO.setId(lecturerId);
        lecturerRepository.save(lecturerMapper.mapLecturerDTOtoLecturer(lecturerDTO));
    }

    public void deleteLecturer(long lecturerId){
        lecturerRepository.deleteById(lecturerId);
    }


}
