package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Presence;
import PGS.JAVADEV.PGS.Student.Presence.List.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/pgs/presences")
public class PresenceController {
    @Autowired
    PresenceService presenceService;


    //TODO zmienić sygnaturę na studenta, datę i przedmiot
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

   @RequestMapping(method = RequestMethod.POST, value = "/student/{studentId}/subject/{subjectId}")
    public ResponseEntity<?> createPresence(@RequestBody Presence presence, @PathVariable("{studentId}") long studentId, @PathVariable("subjectId") long subjectId){
        if(presenceService.isPresenceExist(presence)){
            throw new RuntimeException("Presence Already exist");
        }
        presenceService.save(presence);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/{studentId}/subject/{subjectId}")
    public ResponseEntity<?> getStudentSubject(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId){
        return new ResponseEntity<Object>(presenceService.getPresenceByStudentIdAndSubjects(studentId, subjectId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updatePresence(@PathVariable("id") long id, @RequestBody Presence presence){
        Presence currentPresence = presenceService.findById(id);
        presence.setId(currentPresence.getId());
        presenceService.save(presence);

        return new ResponseEntity<Presence>(presence, HttpStatus.OK);
    }


}
