package PGS.JAVADEV.PGS.Student.Presence.List.controller;


import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.StudentSubject;
import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





import java.util.Set;

@RestController
@RequestMapping("/pgs/subjects")
public class  SubjectController {
    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Subject>> getAllSubjects(){

      Set<Subject>  subjects= subjectService.findAllSubjects();
       return new ResponseEntity<Set<Subject>>(subjects, HttpStatus.OK);

    }
   @RequestMapping(value = "/{subjectId}",method = RequestMethod.GET)
    public ResponseEntity<?> getSubjectById(@PathVariable("subjectId") long id){
        Subject subject = subjectService.findById(id);
        return  new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }
@RequestMapping(value = "/{subjectName}",method = RequestMethod.GET)
    public ResponseEntity<?> getSubjectByName(@PathVariable("subjectName") String name){
        Subject subject = subjectService.findByName(name);
        return  new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }


    @RequestMapping(value = "/{subjectId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSubject(@PathVariable("subjectId") long id){
        getSubjectById(id);
        deleteSubject(id);
        return  new ResponseEntity<Subject>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createSubject(@RequestBody Subject subject){
       if(subjectService.isSubjectExist(subject)){
            throw new RuntimeException("Subject Already exist");
        }
        subjectService.save(subject);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateSubject(@PathVariable("id") long id, @RequestBody Subject subject){
        Subject currentSubject = subjectService.findById(id);
        subject.setId(currentSubject.getId());
        subjectService.save(subject);

        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }
    @RequestMapping(value = "/{studentId}/{subjectId}",method = RequestMethod.POST)
    public ResponseEntity<?>addStudentToSubject(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId){
        subjectService.addStudentToSubject(studentId,subjectId);
        return new ResponseEntity<Object>( HttpStatus.OK);
    }
    @RequestMapping(value = "/grade/{studentId}/{subjectId}",method = RequestMethod.POST)
    public ResponseEntity<?>addGrade(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId, @RequestBody StudentSubject studentSubject){
        subjectService.addGradeToStudent(studentId,subjectId,studentSubject.getGrade());
        return new ResponseEntity<Object>( HttpStatus.OK);
    }






}
