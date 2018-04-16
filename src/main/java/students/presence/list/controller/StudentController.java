package students.presence.list.controller;

import students.presence.list.dto.StudentDTO;
import students.presence.list.service.StudentService;
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
    public Set<StudentDTO> getAllStudents(){
        Set<StudentDTO> studentDTOS = studentService.findAllStudents();
        return studentDTOS;

    }
    @GetMapping({"/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find students by Id ",response = Iterable.class)
    @PostAuthorize("returnoObject.id == authentication.id")
    public StudentDTO getStudentById(@PathVariable("studentId") long id){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        StudentDTO studentDTO = studentService.findById(id);
        return studentDTO;
    }

    @GetMapping( "/byName/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find students by First and Last Name",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public StudentDTO getStudentByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName ){
        StudentDTO studentDTO = studentService.findByFirstNameAndLastName(firstName, lastName);
        return studentDTO;
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete StudentDTO",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudentById(@PathVariable("studentId") Long id)
    {
        studentService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add StudentDTO",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void createStudent(@RequestBody StudentDTO studentDTO){
        if(!studentService.isStudentExist(studentDTO))
        studentService.save(studentDTO);

    }
    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update StudentDTO",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateStudent(@PathVariable("id") long id, @Valid @RequestBody StudentDTO studentDTO){
        StudentDTO currentStudentDTO = studentService.findById(id);
        studentDTO.setId(currentStudentDTO.getId());
        studentService.save(studentDTO);

    }
   @PostMapping("/{studentId}/add/{subjectId}")
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Update StudentDTO",response = Iterable.class)
   @PreAuthorize("hasRole('ADMIN')")
    public void addSubjectToStudent(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId){
        studentService.addSubjectToStudent(studentId,subjectId);
    }



}
