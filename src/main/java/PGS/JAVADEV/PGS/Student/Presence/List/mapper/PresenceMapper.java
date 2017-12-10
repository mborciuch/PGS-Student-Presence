package PGS.JAVADEV.PGS.Student.Presence.List.mapper;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Presence;
import PGS.JAVADEV.PGS.Student.Presence.List.model.PresenceEntity;
import org.springframework.stereotype.Component;

@Component
public class PresenceMapper {


    public PresenceEntity mapPresenceToPresenceEntity (Presence presence){
        PresenceEntity presenceEntity = new PresenceEntity();
        presenceEntity.setDate(presence.getDate());
        presenceEntity.setPresence(presence.isPresence());
        return presenceEntity;

    }

    public Presence mapPresenceEntityToPresence(PresenceEntity presenceEntity){
        Presence presence = new Presence();
        presenceEntity.setId(presence.getId());
        presenceEntity.setDate(presence.getDate());
        presenceEntity.setPresence(presence.isPresence());
        return presence;
    }
}
