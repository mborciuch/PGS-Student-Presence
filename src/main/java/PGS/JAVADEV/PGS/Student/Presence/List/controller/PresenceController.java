package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Presence;
import PGS.JAVADEV.PGS.Student.Presence.List.service.PresenceService;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Set;

import static PGS.JAVADEV.PGS.Student.Presence.List.controller.PresenceController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class PresenceController {


    public static final String BASE_URL = "/pgs/presences";

    @Autowired
    PresenceService presenceService;

    @Autowired
    StudentService studentService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping({"/{subjectId}/{studentId}"})
    public Set<Presence> getPresenceOfStudentFromSubject(@PathVariable("subjectId") long subjectId, @PathVariable("subjectId") long studentId ){
        Set<Presence> presences = presenceService.getPresenceByStudentIdAndSubjects(subjectId, studentId);
        return  presences;
    }
    @DeleteMapping(("/{subjectId}/{studentId}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePresence(@RequestBody Presence presence, @PathVariable("subjectId") long subjectId, @PathVariable("{studentId}") long studentId){
        presenceService.delete(subjectId,studentId,presence.getDate());
    }

    @PostMapping("/{subjectId}/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPresence(@RequestBody Presence presence,  @PathVariable("subjectId") long subjectId, @PathVariable("{studentId}") long studentId){
        if(presenceService.isPresenceExist(presence)){
            throw new RuntimeException("Presence Already exist");
        }
        presenceService.save(presence, subjectId, studentId);
    }


    @PutMapping("/{subjectId}/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePresence(@RequestBody Presence presence,  @PathVariable("subjectId") long subjectId, @PathVariable("{studentId}") long studentId){
        Presence currentPresence = presenceService.findByStudentSubjectAndDate(subjectId,studentId,presence.getDate());
        presence.setId(currentPresence.getId());
        presenceService.save(presence, studentId, subjectId);

    }


}
