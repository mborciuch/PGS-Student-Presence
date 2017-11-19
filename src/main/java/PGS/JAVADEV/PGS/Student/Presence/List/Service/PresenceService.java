package PGS.JAVADEV.PGS.Student.Presence.List.Service;

import PGS.JAVADEV.PGS.Student.Presence.List.DTO.Presence;
import PGS.JAVADEV.PGS.Student.Presence.List.Model.PresenceEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.Repositories.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PresenceService {
    @Autowired
    PresenceRepository presenceRepository;

    public List<Presence> findAllPresences(){
        Iterable<PresenceEntity> presenceEntities = presenceRepository.findAll();
        List<Presence> presences = new ArrayList<>();
        presenceEntities.forEach(presence -> presences .add(map(presence)));
        return presences;
    }

    public Presence findById(long id){
        PresenceEntity presenceEntity = presenceRepository.findById(id);
        if(presenceEntity == null) {
            return null;
        }
        return map(presenceRepository.findById(id));
    }
    public void save(Presence subject){
        presenceRepository.save(map(subject));
    }
    public void delete(long id){
        presenceRepository.deleteById(id);
    }

    public PresenceEntity map (Presence presence){
        PresenceEntity presenceEntity = new PresenceEntity();
        presenceEntity.setId(presence.getId());
        presenceEntity.setDate(presence.getDate());
        presenceEntity.setPresence(presence.isPresence());

        return presenceEntity;

    }

    public Presence map(PresenceEntity presenceEntity){

        Presence presence = new Presence();
        presenceEntity.setId(presence.getId());
        presenceEntity.setDate(presence.getDate());
        presenceEntity.setPresence(presence.isPresence());

        return presence;
    }
}
