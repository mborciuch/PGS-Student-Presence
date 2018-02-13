package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Presence;
import PGS.JAVADEV.PGS.Student.Presence.List.service.PresenceService;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import com.sun.xml.internal.bind.v2.TODO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Set;

import static PGS.JAVADEV.PGS.Student.Presence.List.controller.PresenceController.BASE_URL;

@RestController
@RequestMapping(PresenceController.BASE_URL)
@Api(value="Pgs-presence-list", description="Operations related to Presences of Students")
public class PresenceController {


    public static final String BASE_URL = "/presences";

    @Autowired
    PresenceService presenceService;

    @Autowired
    StudentService studentService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find presences of student from some subject",response = Iterable.class)
    @RequestMapping({"/{subjectId}/{studentId}"})
    public Set<Presence> getPresenceOfStudentFromSubject(@PathVariable("subjectId") long subjectId, @PathVariable("subjectId") long studentId ){
        Set<Presence> presences = presenceService.getPresenceByStudentIdAndSubjects(subjectId, studentId);
       return  presences;
    }


    @PostMapping("/{subjectId}/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Presence for student from subject",response = Iterable.class)
    public void createPresence(@Valid @RequestBody Presence presence,  @PathVariable("subjectId") long subjectId, @PathVariable("studentId") long studentId){
        //Todo: checking excisting presence
        presenceService.save(presence, subjectId, studentId);
    }


    @PutMapping("/{subjectId}/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update presence Presence",response = Iterable.class)
    public void updatePresence(@Valid @RequestBody Presence presence,  @PathVariable("subjectId") long subjectId, @PathVariable("{studentId}") long studentId){
        Presence currentPresence = presenceService.findByStudentSubjectAndDate(subjectId,studentId,presence.getDate());
        presence.setId(currentPresence.getId());
        presenceService.save(presence, studentId, subjectId);

    }
    @DeleteMapping(("/{subjectId}/{studentId}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Presence",response = Iterable.class)
    public void deletePresence(@Valid @RequestBody Presence presence, @PathVariable("subjectId") long subjectId, @PathVariable("{studentId}") long studentId){
        presenceService.delete(subjectId,studentId,presence.getDate());
    }


}
