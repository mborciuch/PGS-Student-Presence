package PGS.JAVADEV.PGS.Student.Presence.List.controller;


import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.StudentSubject;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEnum;
import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import PGS.JAVADEV.PGS.Student.Presence.List.service.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.print.DocFlavor;
import javax.validation.Valid;
import java.util.Set;

import static PGS.JAVADEV.PGS.Student.Presence.List.controller.SubjectController.BASE_URL;

@RestController
@RequestMapping(SubjectController.BASE_URL)
@Api(value="Pgs-presence-list", description="Operations related to Subjects")
public class  SubjectController {


    SubjectService subjectService;




    public static final String BASE_URL = "/subjects";

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of all subjects",response = Iterable.class)
    public Set<Subject> getAllSubjects(){
       Set<Subject>  subjects = subjectService.findAllSubjects();
       return subjects;
    }

    @GetMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subject by Id",response = Iterable.class)
    public Subject getSubjectById(@PathVariable("subjectId") long id){
        Subject subject = subjectService.findById(id);
        return  subject;
    }

    @GetMapping({"/byName/{subjectName}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subject by Name",response = Iterable.class)
    public Subject getSubjectByName(@PathVariable("subjectName") String name){
        Subject subject = subjectService.findByName(name);
        return  subject;
    }

    @DeleteMapping ({"/{subjectId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Subject",response = Iterable.class)
    public void deleteSubject(@PathVariable("subjectId") long id){
        subjectService.delete(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Subject",response = Iterable.class)
    public void createSubject(@Valid @RequestBody Subject subject){
 /*      if(subjectService.isSubjectExist(subject)){
            throw new RuntimeException("Subject Already exist");

        }*/
        subjectService.save(subject);

    }
    @PutMapping( {"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Subject",response = Iterable.class)
    public void updateSubject(@PathVariable("subjectId") long id, @Valid @RequestBody Subject subject){
        Subject currentSubject = subjectService.findById(id);
        subject.setId(currentSubject.getId());
        subjectService.save(subject);

    }

   @PostMapping ({"/{subjectId}/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Add Student To Subject",response = Iterable.class)
    public void addStudentToSubject(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId){
        subjectService.addStudentToSubject(studentId,subjectId);
    }

    @PostMapping ({"/{subjectId}/{studentId}/addGrade"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add Grade To Student from some Subject",response = Iterable.class)
    public void addGrade(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId, @Valid @RequestBody GradeEnum gradeEnum){ ;
        subjectService.addGradeToStudent(studentId,subjectId,gradeEnum);

    }






}
