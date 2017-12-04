package PGS.JAVADEV.PGS.Student.Presence.List.dto;

import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEnum;

public class StudentSubject {
    private Long subjectId;
    private String subjectName;
    private String lecturer;



    private GradeEnum grade;

    public StudentSubject() {
    }

    public StudentSubject(Long subjectId, String subjectName, GradeEnum grade) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public GradeEnum getGrade() {
        return grade;
    }

    public void setGrade(GradeEnum grade) {
        this.grade = grade;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}
