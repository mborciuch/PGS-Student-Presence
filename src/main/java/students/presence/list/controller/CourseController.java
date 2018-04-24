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
@RequestMapping(CourseController.BASE_URL)
@Api(value = "Pgs-presence-list", description = "Operations related to Courses")
public class CourseController {


    public static final String BASE_URL = "/courses";

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of all courses", response = Iterable.class)
    public Set<CourseDTO> getAllCourses() {
        Set<CourseDTO> courseDTOS = courseService.findAllSubjects();
        return courseDTOS;
    }

    @GetMapping({"/{courseId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subject by Id", response = Iterable.class)
    public CourseDTO getCourseById(@PathVariable("coursesId") long id) {
        CourseDTO courseDTO = courseService.findById(id);
        return courseDTO;
    }

    @GetMapping({"/{courseName}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find courses by Name", response = Iterable.class)
    public Set<CourseDTO> getCourseByName(@PathVariable("courseName") String name) {
        Set<CourseDTO> courseDTO = courseService.findByName(name);
        return courseDTO;
    }

    @DeleteMapping({"/{courseId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Course", response = Iterable.class)
    public void deleteCourse(@PathVariable("courseId") long id) {
        courseService.deleteCourse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Course", response = Iterable.class)
    public void createSubject(@Valid @RequestBody CourseDTO courseDTO) {
        courseService.saveCourse(courseDTO);
    }

    @PutMapping({"/{courseId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Course", response = Iterable.class)
    public void updateSubject(@PathVariable("courseId") long courseId, @Valid @RequestBody CourseDTO courseDTO) {
        courseService.updateCourse(courseId, courseDTO);
    }

    @PutMapping({"/{courseId}/student/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add StudentDTO To CourseDTO", response = Iterable.class)
    public void addStudentToSubject(@PathVariable("studentId") long studentId, @PathVariable("courseId") long courseId) {
        courseService.addStudentToCourse(studentId, courseId);
    }

    //ToDo
    //Dodawanie oceny: usunięcie Grade z URL
    @PutMapping({"/{courseId}/student/{studentId}/"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add Grade To Student enrolled to some Course", response = Iterable.class)
    public void addGrade(@PathVariable("studentId") long studentId, @PathVariable("courseId") long courseId, @Valid @RequestBody Grade grade) {
        courseService.addGradeToStudent(studentId, courseId, grade);
    }


}
