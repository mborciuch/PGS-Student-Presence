package PGS.JAVADEV.PGS.Student.Presence.List.controller;


import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.StudentSubject;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEnum;
import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import PGS.JAVADEV.PGS.Student.Presence.List.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.print.DocFlavor;
import java.util.Set;

import static PGS.JAVADEV.PGS.Student.Presence.List.controller.SubjectController.BASE_URL;

@RestController
@RequestMapping(SubjectController.BASE_URL)
public class  SubjectController {


    SubjectService subjectService;




    public static final String BASE_URL = "/subjects";

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Subject> getAllSubjects(){
       Set<Subject>  subjects = subjectService.findAllSubjects();
       return subjects;
    }

    @GetMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    public Subject getSubjectById(@PathVariable("subjectId") long id){
        Subject subject = subjectService.findById(id);
        return  subject;
    }

    @GetMapping({"/byName/{subjectName}"})
    @ResponseStatus(HttpStatus.OK)
    public Subject getSubjectByName(@PathVariable("subjectName") String name){
        Subject subject = subjectService.findByName(name);
        return  subject;
    }

    @DeleteMapping ({"/{subjectId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable("subjectId") long id){
        subjectService.delete(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void createSubject(@RequestBody Subject subject){
 /*      if(subjectService.isSubjectExist(subject)){
            throw new RuntimeException("Subject Already exist");

        }*/
        subjectService.save(subject);

    }
    @PutMapping( {"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    public void updateSubject(@PathVariable("subjectId") long id, @RequestBody Subject subject){
        Subject currentSubject = subjectService.findById(id);
        subject.setId(currentSubject.getId());
        subjectService.save(subject);

    }

   @PostMapping ({"/{subjectId}/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    public void addStudentToSubject(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId){
        subjectService.addStudentToSubject(studentId,subjectId);
    }

    @PostMapping ({"/{subjectId}/{studentId}/addGrade"})
    @ResponseStatus(HttpStatus.OK)
    public void addGrade(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId, @RequestBody GradeEnum gradeEnum){ ;
        subjectService.addGradeToStudent(studentId,subjectId,gradeEnum);

    }






}
