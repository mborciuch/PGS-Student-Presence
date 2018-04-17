package students.presence.list.service;

import students.presence.list.dto.AttendanceDTO;
import students.presence.list.mapper.AttendanceMapper;
import students.presence.list.model.Attendance;
import students.presence.list.model.Enrollment;
import students.presence.list.model.Lecture;
import students.presence.list.model.Student;
import students.presence.list.repositories.AttendanceRepository;
import students.presence.list.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;
import students.presence.list.repositories.LectureRepository;
import students.presence.list.repositories.StudentRepository;

import java.util.*;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final StudentRepository studentRepository;

    private final LectureRepository lectureRepository;

    private final AttendanceMapper attendanceMapper;


    public AttendanceService(AttendanceRepository attendanceRepository, EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository, LectureRepository lectureRepository,
                             AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.lectureRepository = lectureRepository;
        this.attendanceMapper = attendanceMapper;
    }

    public AttendanceDTO findAttendancesByStudentIdAndLectureId(Long studentId, Long lectureId) {
        Attendance attendance = attendanceRepository.findByStudentIdAndLectureId(studentId, lectureId);
        AttendanceDTO attendanceDTO = attendanceMapper.mapAttendanceToAttendanceDTO(attendance);
        return attendanceDTO;
    }

    public void saveAttendance(AttendanceDTO attendanceDTO,  long studentId, long lectureId) {
        Attendance attendance = attendanceMapper.mapAttendanceDTOtoAttendance(attendanceDTO);
        Student student = studentRepository.findById(studentId);
        Lecture lecture = lectureRepository.findById(lectureId);
        attendance.setStudent(student);
        attendance.setLecture(lecture);
        attendanceRepository.saveAttendance(attendance);
    }
    //ToDo
    //Tylko te co się nie odbyły
    public void deleteAttendance(long studentId, long lectureId) {
        attendanceRepository.deleteByStudentIdAndLectureId(studentId,  lectureId);
    }
}
