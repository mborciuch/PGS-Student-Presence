package students.presence.list.mapper;

import org.springframework.stereotype.Component;
import students.presence.list.dto.AttendanceDTO;
import students.presence.list.model.Attendance;

@Component
public class AttendanceMapper {
    public Attendance mapAttendanceDTOtoAttendance(AttendanceDTO attendanceDTO){
        Attendance attendance = new Attendance();
        attendance.setDate(attendanceDTO.getDate());

        return attendance;
    }

    public AttendanceDTO mapAttendanceToAttendanceDTO(Attendance attendance){
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(attendance.getId());
        attendanceDTO.setDate(attendance.getDate());
        attendanceDTO.setLectureName(attendance.getLecture().getName());
        attendanceDTO.setStudentFirstName(attendance.getStudent().getFirstName());
        attendanceDTO.setStudentLastName(attendance.getStudent().getLastName());
        return attendanceDTO;
    }
}
