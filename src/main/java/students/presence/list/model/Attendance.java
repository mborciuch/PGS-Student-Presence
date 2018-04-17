package students.presence.list.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    Date date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    Lecture lecture;

    public Attendance() {
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendance that = (Attendance) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        return lecture != null ? lecture.equals(that.lecture) : that.lecture == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (lecture != null ? lecture.hashCode() : 0);
        return result;
    }
}
