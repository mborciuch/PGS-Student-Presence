package students.presence.list.controller;


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
import students.presence.list.dto.LecturerDTO;

import students.presence.list.model.Lecturer;
import students.presence.list.service.LecturerService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(LecturerController.BASE_URL)
@Api(value = "Pgs-presence-list", description = "Operations related to Lecturers")
public class LecturerController {

    public static final String BASE_URL = "/lecturers";

    @Autowired
    LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of all students",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @PreAuthorize("hasRole('ADMIN')")
    public Set<LecturerDTO> getAllLecturers(){
        Set<LecturerDTO> lecturerDTOSDTOS = lecturerService.findAllLecturers();
        return lecturerDTOSDTOS;
    }

    @GetMapping({"/{lecturerId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find lecturer by Id ",response = Iterable.class)
    @PostAuthorize("returnoObject.id == authentication.id")
    public LecturerDTO getLecturerById(@PathVariable("lecturerId") long lecturerId){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        LecturerDTO lecturerDTO = lecturerService.findById(lecturerId);
        return lecturerDTO;
    }

    @GetMapping( "/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find lecturer by First and Last Name",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public Set<LecturerDTO> getLecturerByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName ){
        Set<LecturerDTO> lecturerDTOS = lecturerService.findByFirstNameAndLastName(firstName, lastName);
        return lecturerDTOS;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add Lecturer",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void createLecturer(@RequestBody LecturerDTO lecturerDTO){
        lecturerService.saveLecturer(lecturerDTO);
    }

    @PutMapping("/{lecturerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update lecturer",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateLecturer(@PathVariable("lecturerId") long lecturerId, @Valid @RequestBody LecturerDTO lecturerDTO){
        lecturerService.updateLecturer(lecturerId, lecturerDTO);
    }

    @DeleteMapping("{lecturerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Lecturer",response = Iterable.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLecturer(@PathVariable("lecturerId") long lecturerId) {
        lecturerService.deleteLecturer(lecturerId);
    }
}
