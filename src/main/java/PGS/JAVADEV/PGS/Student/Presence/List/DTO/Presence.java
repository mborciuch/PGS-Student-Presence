package PGS.JAVADEV.PGS.Student.Presence.List.DTO;



public class Presence {
    private String date;
    private Subject subject ;
    private boolean presence;
    private Student student;

    public Presence(String date, Subject subject, boolean presence, Student student) {
        this.date = date;
        this.subject = subject;
        this.presence = presence;
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
