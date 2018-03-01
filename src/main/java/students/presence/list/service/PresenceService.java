package students.presence.list.service;

import students.presence.list.dto.PresenceDTO;
import students.presence.list.model.Presence;
import students.presence.list.model.StudentSubject;
import students.presence.list.repositories.PresenceRepository;
import students.presence.list.repositories.StudentsSubjectRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PresenceService {

    private final PresenceRepository presenceRepository;
    private final StudentsSubjectRepository studentsSubjectRepository;


    public PresenceService(PresenceRepository presenceRepository,
                           StudentsSubjectRepository studentsSubjectRepository) {
        this.presenceRepository = presenceRepository;
        this.studentsSubjectRepository = studentsSubjectRepository;
    }

    public Set<PresenceDTO> getPresenceByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        StudentSubject studentSubject = studentsSubjectRepository.findAllByStudentIdAndSubjectId(studentId, subjectId);
        Set<Presence> presences = studentSubject.getPresence();
        Set<PresenceDTO> presenceDTOS = new HashSet<>();
        presences.forEach(presence -> presenceDTOS.add(mapPresenceToPresenceDTO(presence)));
        return presenceDTOS;
    }

    @Deprecated
    public List<PresenceDTO> findAllPresences() {
        Iterable<Presence> presenceEntities = presenceRepository.findAll();
        List<PresenceDTO> presenceDTOS = new ArrayList<>();
        presenceEntities.forEach(presence -> presenceDTOS.add(mapPresenceToPresenceDTO(presence)));
        return presenceDTOS;
    }

    public PresenceDTO findByStudentSubjectAndDate(long subjectId, long studentId, Date date) {
        StudentSubject studentSubject = studentsSubjectRepository
                .findByStudentIdAndSubjectId(studentId, subjectId);
        Presence presence = presenceRepository.findByStudentSubjectAndDate(studentSubject, date);
        if (presence == null) {
            return null;
        }
        return mapPresenceToPresenceDTO(presence);
    }

    public void save(PresenceDTO presenceDTO, long subjectId, long studentId) {
        presenceRepository.save(mapPresenceDTOToPresence(presenceDTO));
        StudentSubject studentSubject = studentsSubjectRepository
                .findByStudentIdAndSubjectId(studentId, subjectId);
        studentSubject.getPresence().add(mapPresenceDTOToPresence(presenceDTO));
    }


    public void delete(long subjectId, long studentId, Date date) {
        StudentSubject studentSubject = studentsSubjectRepository
                .findAllByStudentIdAndSubjectId(studentId, subjectId);
        Presence currentPresence = presenceRepository.findByStudentSubjectAndDate(studentSubject, date);
        presenceRepository.delete(currentPresence);
    }

    public Presence mapPresenceDTOToPresence(PresenceDTO presenceDTO) {
        Presence presence = new Presence();
        presence.setDate(presenceDTO.getDate());
        presence.setPresence(presenceDTO.isPresence());
        return presence;
    }

    public PresenceDTO mapPresenceToPresenceDTO(Presence presence) {
        PresenceDTO presenceDTO = new PresenceDTO();
        presence.setId(presenceDTO.getId());
        presence.setDate(presenceDTO.getDate());
        presence.setPresence(presenceDTO.isPresence());
        return presenceDTO;
    }


}
