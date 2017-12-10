package PGS.JAVADEV.PGS.Student.Presence.List.controller;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Student;
import PGS.JAVADEV.PGS.Student.Presence.List.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(StudentController.BASE_URL)
@Api(value="Pgs-presence-list", description="Operations related to Students")
public class StudentController {

    public static final String BASE_URL = "/students";

    @Autowired
    StudentService studentService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of all students",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @PreAuthorize("hasRole('ADMIN')")
    public Set<Student> getAllStudents(){
        Set<Student> students = studentService.findAllStudents();
        return students;

    }
    @GetMapping({"/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find students by Id ",response = Iterable.class)
    //@PostAuthorize()
    public Student getStudentById(@PathVariable("studentId") long id){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Student student = studentService.findById(id);
        return  student;
    }
    @GetMapping( "/{firstName/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find students by First and Last Name",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public Student getStudentByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName ){
        Student student = studentService.findByFirstNameAndLastName(firstName, lastName);
        return  student;
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Student",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudentById(@PathVariable("studentId") Long id)
    {
        studentService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add Student",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void createStudent(@RequestBody Student student){
        if(!studentService.isStudentExist(student))
        studentService.save(student);

    }
    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Student",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateStudent(@PathVariable("id") long id, @Valid @RequestBody Student student){
        Student currentStudent = studentService.findById(id);
        student.setId(currentStudent.getId());
        studentService.save(student);

    }
   @PostMapping("/{studentId}/add/{subjectId}")
    @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Update Student",response = Iterable.class)
   @PreAuthorize("hasRole('ADMIN')")
    public void addSubjectToStudent(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId){
        studentService.addSubjectToStudent(studentId,subjectId);
    }



}
