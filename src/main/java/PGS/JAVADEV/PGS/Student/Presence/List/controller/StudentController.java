package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pgs/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student>  students = studentService.findAllStudents();
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);

    }
    @RequestMapping(value = "/{StudentId}",method = RequestMethod.GET)
    public ResponseEntity<?> getStudentById(@PathVariable("StudentId") long id){
        Student student = studentService.findById(id);
        return  new ResponseEntity<Student>(student, HttpStatus.OK);
    }
    @RequestMapping(value = "/{StudentId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable("StudentId") long id){
        getStudentById(id);
        deleteStudent(id);
        return  new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student Student){
        if(studentService.isStudentExist(Student)){
            throw new RuntimeException("Student Already exist");
        }
        studentService.save(Student);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @RequestMapping(value = "/id",method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") long id, @RequestBody Student Student){
        Student currentStudent = studentService.findById(id);
        Student.setId(currentStudent.getId());
        studentService.save(Student);

        return new ResponseEntity<Student>(Student, HttpStatus.OK);
    }

}
