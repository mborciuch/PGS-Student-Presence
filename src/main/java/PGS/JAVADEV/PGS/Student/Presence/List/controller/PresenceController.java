package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Presence;
import PGS.JAVADEV.PGS.Student.Presence.List.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pgs/presences")
public class PresenceController {
    @Autowired
    PresenceService presenceService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Presence>> getAllPresences(){

        List<Presence>  presences= presenceService.findAllPresences();
        return new ResponseEntity<List<Presence>>(presences, HttpStatus.OK);

    }
    @RequestMapping(value = "/{presenceId}",method = RequestMethod.GET)
    public ResponseEntity<?> getPresenceById(@PathVariable("presenceId") long id){
        Presence presence = presenceService.findById(id);
        return  new ResponseEntity<Presence>(presence, HttpStatus.OK);
    }
    @RequestMapping(value = "/{presenceId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePresence(@PathVariable("presenceId") long id){
        getPresenceById(id);
        deletePresence(id);
        return  new ResponseEntity<Presence>(HttpStatus.NO_CONTENT);
    }
/*    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createPresence(@RequestBody Presence presence){
        if(presenceService.isPresenceExist(presence)){
            throw new RuntimeException("Presence Already exist");
        }
        presenceService.save(presence);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }*/
    @RequestMapping(value = "/id",method = RequestMethod.PUT)
    public ResponseEntity<?> updatePresence(@PathVariable("id") long id, @RequestBody Presence presence){
        Presence currentPresence = presenceService.findById(id);
        presence.setId(currentPresence.getId());
        presenceService.save(presence);

        return new ResponseEntity<Presence>(presence, HttpStatus.OK);
    }


}
