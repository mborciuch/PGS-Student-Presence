package students.presence.list.mapper;

import students.presence.list.dto.PresenceDTO;
import students.presence.list.model.Presence;
import org.springframework.stereotype.Component;

@Component
public class PresenceMapper {


    public Presence mapPresenceDTOToPresence (PresenceDTO presenceDTO){
        Presence presence = new Presence();
        presence.setDate(presenceDTO.getDate());
        presence.setPresence(presenceDTO.isPresence());
        return presence;

    }

    public PresenceDTO mapPresenceToPresenceDTO(Presence presence){
        PresenceDTO presenceDTO = new PresenceDTO();
        presence.setId(presenceDTO.getId());
        presence.setDate(presenceDTO.getDate());
        presence.setPresence(presenceDTO.isPresence());
        return presenceDTO;
    }
}
