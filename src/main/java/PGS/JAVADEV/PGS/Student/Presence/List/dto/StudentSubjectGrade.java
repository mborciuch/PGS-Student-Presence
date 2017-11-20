package PGS.JAVADEV.PGS.Student.Presence.List.dto;

public class StudentSubjectGrade {
    private Student student;
    private Subject subject;
    private  Grade grade;

    public StudentSubjectGrade() {
    }

    public StudentSubjectGrade(Student student, Subject subject, Grade grade) {
        this.student = student;
        this.subject = subject;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentSubjectGrade{" +
                "student=" + student +
                ", subject=" + subject +
                ", grade=" + grade +
                '}';
    }
}
