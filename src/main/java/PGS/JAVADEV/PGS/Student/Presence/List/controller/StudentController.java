package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(StudentController.BASE_URL)
public class StudentController {

    public static final String BASE_URL = "/students";

    @Autowired
    StudentService studentService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Student> getAllStudents(){
        Set<Student> students = studentService.findAllStudents();
        return students;

    }
    @GetMapping({"/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable("studentId") long id){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Student student = studentService.findById(id);
        return  student;
    }
    @GetMapping( "/{firstName/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName ){
        Student student = studentService.findByFirstNameAndLastName(firstName, lastName);
        return  student;
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable("studentId") Long id)
    {
        studentService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student){
        if(!studentService.isStudentExist(student))
        studentService.save(student);

    }
    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@PathVariable("id") long id, @RequestBody Student student){
        Student currentStudent = studentService.findById(id);
        student.setId(currentStudent.getId());
        studentService.save(student);

    }
   @PostMapping("/{studentId}/add/{subjectId}")
    @ResponseStatus(HttpStatus.OK)
    public void addSubjectToStudent(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId){
        studentService.addSubjectToStudent(studentId,subjectId);
    }



}
