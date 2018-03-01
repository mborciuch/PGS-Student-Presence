package students.presence.list.controller;


import students.presence.list.dto.SubjectDTO;
import students.presence.list.model.GradeEnum;
import students.presence.list.service.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(SubjectController.BASE_URL)
@Api(value = "Pgs-presence-list", description = "Operations related to Subjects")
public class SubjectController {


    SubjectService subjectService;


    public static final String BASE_URL = "/subjects";

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of all subjects", response = Iterable.class)
    public Set<SubjectDTO> getAllSubjects() {
        Set<SubjectDTO> subjectDTOS = subjectService.findAllSubjects();
        return subjectDTOS;
    }

    @GetMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subject by Id", response = Iterable.class)
    public SubjectDTO getSubjectById(@PathVariable("subjectId") long id) {
        SubjectDTO subjectDTO = subjectService.findById(id);
        return subjectDTO;
    }

    @GetMapping({"/byName/{subjectName}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subject by Name", response = Iterable.class)
    public SubjectDTO getSubjectByName(@PathVariable("subjectName") String name) {
        SubjectDTO subjectDTO = subjectService.findByName(name);
        return subjectDTO;
    }

    @DeleteMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete SubjectDTO", response = Iterable.class)
    public void deleteSubject(@PathVariable("subjectId") long id) {
        subjectService.delete(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create SubjectDTO", response = Iterable.class)
    public void createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
 /*      if(subjectService.isSubjectExist(subjectDTO)){
            throw new RuntimeException("SubjectDTO Already exist");

        }*/
        subjectService.save(subjectDTO);

    }

    @PutMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update SubjectDTO", response = Iterable.class)
    public void updateSubject(@PathVariable("subjectId") long id, @Valid @RequestBody SubjectDTO subjectDTO) {
        SubjectDTO currentSubjectDTO = subjectService.findById(id);
        subjectDTO.setId(currentSubjectDTO.getId());
        subjectService.save(subjectDTO);

    }

    @PostMapping({"/{subjectId}/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add StudentDTO To SubjectDTO", response = Iterable.class)
    public void addStudentToSubject(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId) {
        subjectService.addStudentToSubject(studentId, subjectId);
    }

    @PostMapping({"/{subjectId}/{studentId}/addGrade"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add Grade To StudentDTO from some SubjectDTO", response = Iterable.class)
    public void addGrade(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId, @Valid @RequestBody GradeEnum gradeEnum) {
        ;
        subjectService.addGradeToStudent(studentId, subjectId, gradeEnum);

    }


}
