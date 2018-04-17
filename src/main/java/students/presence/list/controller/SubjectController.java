package students.presence.list.controller;


import students.presence.list.dto.CourseDTO;
import students.presence.list.model.Grade;
import students.presence.list.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(SubjectController.BASE_URL)
@Api(value = "Pgs-presence-list", description = "Operations related to Subjects")
public class SubjectController {


    public static final String BASE_URL = "/subjects";

    CourseService courseService;

    public SubjectController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of all subjects", response = Iterable.class)
    public Set<CourseDTO> getAllSubjects() {
        Set<CourseDTO> courseDTOS = courseService.findAllSubjects();
        return courseDTOS;
    }

    @GetMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subject by Id", response = Iterable.class)
    public CourseDTO getSubjectById(@PathVariable("subjectId") long id) {
        CourseDTO courseDTO = courseService.findById(id);
        return courseDTO;
    }

    @GetMapping({"/{subjectName}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subject by Name", response = Iterable.class)
    public Set<CourseDTO> getSubjectByName(@PathVariable("subjectName") String name) {
        Set<CourseDTO> courseDTO = courseService.findByName(name);
        return courseDTO;
    }

    @DeleteMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete CourseDTO", response = Iterable.class)
    public void deleteSubject(@PathVariable("subjectId") long id) {
        courseService.deleteCourse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create CourseDTO", response = Iterable.class)
    public void createSubject(@Valid @RequestBody CourseDTO courseDTO) {
        //ToDo
        //Gdzie rzucić wyjątek
        /*if (courseService.isSubjectExist(courseDTO)) {
            throw new RuntimeException("CourseDTO Already exist");

        }*/
        courseService.saveCourse(courseDTO);
    }

    @PutMapping({"/{subjectId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update CourseDTO", response = Iterable.class)
    public void updateSubject(@PathVariable("subjectId") long id, @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO currentCourseDTO = courseService.findById(id);
        courseDTO.setId(currentCourseDTO.getId());
        courseService.saveCourse(courseDTO);
    }

    @PostMapping({"/{subjectId}/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add StudentDTO To CourseDTO", response = Iterable.class)
    public void addStudentToSubject(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId) {
        courseService.addStudentToCourse(studentId, subjectId);
    }

    //ToDo
    //Dodawanie oceny: usunięcie Grade z URL
    @PostMapping({"/{subjectId}/{studentId}/addGrade"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add Grade To StudentDTO from some CourseDTO", response = Iterable.class)
    public void addGrade(@PathVariable("studentId") long studentId, @PathVariable("subjectId") long subjectId, @Valid @RequestBody Grade grade) {
        courseService.addGradeToStudent(studentId, subjectId, grade);
    }


}
