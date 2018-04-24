package students.presence.list.controller;

import students.presence.list.dto.AttendanceDTO;
import students.presence.list.service.AttendanceService;
import students.presence.list.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(AttendanceController.BASE_URL)
@Api(value = "Pgs-presence-list", description = "Operations related to Presences of Students")
public class AttendanceController {

    public static final String BASE_URL = "/attendance";

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    StudentService studentService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find presences of student from some subject", response = Iterable.class)
    @RequestMapping({"/student/{studentId}/lecture/{lectureId}"})
    public AttendanceDTO getAttendance(@PathVariable("studentId") long studentId, @PathVariable("lectureId") long lectureId) {
        AttendanceDTO attendanceDTOS = attendanceService.findAttendancesByStudentIdAndLectureId(studentId, lectureId);
        return attendanceDTOS;
    }

    @PostMapping("/student/{studentId}/lecture/{lectureId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Attendance for student from subject", response = Iterable.class)
    public void createAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO, @PathVariable("studentId") long studentId, @PathVariable("lectureId") long lectureId) {
        //Todo: checking excisting presence
        attendanceService.saveAttendance(attendanceDTO, studentId, lectureId);
    }

    @PutMapping("/student/{studentId}/lecture/{lectureId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update attendanceDTO AttendanceDTO", response = Iterable.class)
    public void updatePresence(@Valid @RequestBody AttendanceDTO attendanceDTO, @PathVariable("{studentId}") long studentId,  @PathVariable("lectureId") long lectureId) {
        AttendanceDTO currentAttendanceDTO = attendanceService.findAttendancesByStudentIdAndLectureId(studentId, lectureId);
        attendanceDTO.setId(currentAttendanceDTO.getId());
        attendanceService.saveAttendance(attendanceDTO, studentId, lectureId);
    }

    @DeleteMapping("/student/{studentId}/lecture/{lectureId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete AttendanceDTO", response = Iterable.class)
    public void deletePresence(@Valid @RequestBody AttendanceDTO attendanceDTO, @PathVariable("{studentId}") long studentId, @PathVariable("subjectId") long lectureId) {
        attendanceService.deleteAttendance( studentId, lectureId );
    }


}
