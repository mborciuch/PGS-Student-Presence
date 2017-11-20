package PGS.JAVADEV.PGS.Student.Presence.List.controller;


import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;
import PGS.JAVADEV.PGS.Student.Presence.List.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping("/pgs/subject")
public class  SubjectController {
    @Autowired
    SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Subject>> getAllSubjects(){

       List<Subject>  subjects= subjectService.findAllSubjects();
       return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);

    }
    @RequestMapping(value = "/{subjectId}",method = RequestMethod.GET)
    public ResponseEntity<?> getSubjectById(@PathVariable("subjectId") long id){
        Subject subject = subjectService.findById(id);
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
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @RequestMapping(value = "/id",method = RequestMethod.PUT)
    public ResponseEntity<?> updateSubject(@PathVariable("id") long id, @RequestBody Subject subject){
        Subject currentSubject = subjectService.findById(id);
        subject.setId(currentSubject.getId());
        subjectService.save(subject);

        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }





}
