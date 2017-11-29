package PGS.JAVADEV.PGS.Student.Presence.List.service;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Presence;
import PGS.JAVADEV.PGS.Student.Presence.List.model.PresenceEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.model.StudentSubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.PresenceRepository;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.StudentsSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PresenceService {
    @Autowired
    PresenceRepository presenceRepository;

    @Autowired
    private StudentsSubjectRepository studentsSubjectRepository;
    @Transactional
    public Set<Presence> getPresenceByStudentIdAndSubjects(Long studentId, Long subjectId){
        StudentSubjectEntity studentSubjectEntity = studentsSubjectRepository.findAllByStudentEntityIdAndSubjectEntityId(studentId, subjectId);
        Set<PresenceEntity> presenceEntities = studentSubjectEntity.getPresenceEntity();
        Set<Presence> presences = new HashSet<>();
        presenceEntities.forEach(presence -> presences.add(mapPresenceEntityToPresence(presence)));
        return presences;


    }

    public List<Presence> findAllPresences(){
        Iterable<PresenceEntity> presenceEntities = presenceRepository.findAll();
        List<Presence> presences = new ArrayList<>();
        presenceEntities.forEach(presence -> presences .add(mapPresenceEntityToPresence(presence)));
        return presences;
    }

    public Presence findById(long id){
        PresenceEntity presenceEntity = presenceRepository.findById(id);
        if(presenceEntity == null) {
            return null;
        }
        return mapPresenceEntityToPresence(presenceRepository.findById(id));
    }
    public void save(Presence presence){
        presenceRepository.save(mapPresenceToPresenceEntity(presence));
    }
    public void delete(long id){
        presenceRepository.deleteById(id);
    }

    public boolean isPresenceExist(Presence presence){
        PresenceEntity presenceEntity = presenceRepository.findById(presence.getId());
        if(presence != null){
            return true;
        }
        return false;
    }
    private PresenceEntity mapPresenceToPresenceEntity (Presence presence){
        PresenceEntity presenceEntity = new PresenceEntity();
        presenceEntity.setId(presence.getId());
        presenceEntity.setDate(presence.getDate());
        presenceEntity.setPresence(presence.isPresence());

        return presenceEntity;

    }

    private Presence mapPresenceEntityToPresence(PresenceEntity presenceEntity){
        Presence presence = new Presence();
        presenceEntity.setId(presence.getId());
        presenceEntity.setDate(presence.getDate());
        presenceEntity.setPresence(presence.isPresence());

        return presence;
    }
}
