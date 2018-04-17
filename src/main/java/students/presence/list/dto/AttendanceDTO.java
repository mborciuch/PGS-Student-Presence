package students.presence.list.dto;


import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceDTO {

    private long id;

    @NotNull
    @PastOrPresent
    private Date date;

    private String studentFirstName;

    private String studentLastName;

    private String LectureName;

    public AttendanceDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLasttName) {
        this.studentLastName = studentLasttName;
    }

    public String getLectureName() {
        return LectureName;
    }

    public void setLectureName(String lectureName) {
        LectureName = lectureName;
    }
}
