package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pgs/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Student>> getAllStudents(){

        Set<Student> students = studentService.findAllStudents();
        return new ResponseEntity<Set<Student>>(students, HttpStatus.OK);

    }
    @RequestMapping(value = "/{StudentId}",method = RequestMethod.GET)
    public ResponseEntity<?> getStudentById(@PathVariable("StudentId") long id){
        Student student = studentService.findById(id);
        return  new ResponseEntity<Student>(student, HttpStatus.OK);
    }
    @RequestMapping(value = "/{firstName/{lastName}",method = RequestMethod.GET)
    public ResponseEntity<?> getStudentByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName ){
        Student student = studentService.findByFirstNameAndLastName(firstName, lastName);
        return  new ResponseEntity<Student>(student, HttpStatus.OK);
    }


    @RequestMapping(value = "/{firstName/{lastName}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName ){

        deleteStudent(firstName,lastName);
        return  new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        if(studentService.isStudentExist(student)){
            throw new RuntimeException("Student Already exist");
        }
        studentService.save(student);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") long id, @RequestBody Student student){
        Student currentStudent = studentService.findById(id);
        student.setId(currentStudent.getId());
        studentService.save(student);

        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }


}
