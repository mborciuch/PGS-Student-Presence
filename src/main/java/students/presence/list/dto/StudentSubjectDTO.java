package students.presence.list.dto;

import students.presence.list.model.GradeEnum;

import java.util.HashSet;
import java.util.Set;

public class StudentSubjectDTO {

    private Long subjectId;
    private String subjectName;
    private String lecturer;
    private Set<PresenceDTO> presenceDTOS = new HashSet<>();
    private GradeEnum grade;

    public StudentSubjectDTO() {
    }

    public StudentSubjectDTO(Long subjectId, String subjectName, GradeEnum grade) {
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
