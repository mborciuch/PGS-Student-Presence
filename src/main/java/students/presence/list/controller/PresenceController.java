package students.presence.list.controller;

import students.presence.list.dto.PresenceDTO;
import students.presence.list.service.PresenceService;
import students.presence.list.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(PresenceController.BASE_URL)
@Api(value = "Pgs-presence-list", description = "Operations related to Presences of Students")
public class PresenceController {


    public static final String BASE_URL = "/presences";

    @Autowired
    PresenceService presenceService;
    @Autowired
    StudentService studentService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find presences of student from some subject", response = Iterable.class)
    @RequestMapping({"/{subjectId}/{studentId}"})
    public Set<PresenceDTO> getPresenceOfStudentFromSubject(@PathVariable("subjectId") long subjectId, @PathVariable("subjectId") long studentId) {
        Set<PresenceDTO> presenceDTOS = presenceService.getPresenceByStudentIdAndSubjectId(studentId, subjectId);
        return presenceDTOS;
    }

    @PostMapping("/{subjectId}/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Presence for student from subject", response = Iterable.class)
    public void createPresence(@Valid @RequestBody PresenceDTO presenceDTO, @PathVariable("subjectId") long subjectId, @PathVariable("studentId") long studentId) {
        //Todo: checking excisting presence
        presenceService.save(presenceDTO, subjectId, studentId);
    }

    @PutMapping("/{subjectId}/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update presenceDTO PresenceDTO", response = Iterable.class)
    public void updatePresence(@Valid @RequestBody PresenceDTO presenceDTO, @PathVariable("subjectId") long subjectId, @PathVariable("{studentId}") long studentId) {
        PresenceDTO currentPresenceDTO = presenceService.findByStudentSubjectAndName(subjectId, studentId, presenceDTO.getName());
        presenceDTO.setId(currentPresenceDTO.getId());
        presenceService.save(presenceDTO, studentId, subjectId);
    }

    @DeleteMapping(("/{subjectId}/{studentId}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete PresenceDTO", response = Iterable.class)
    public void deletePresence(@Valid @RequestBody PresenceDTO presenceDTO, @PathVariable("subjectId") long subjectId, @PathVariable("{studentId}") long studentId) {
        presenceService.delete(subjectId, studentId, presenceDTO.getName());
    }


}
