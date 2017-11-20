package PGS.JAVADEV.PGS.Student.Presence.List.dto;

import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEnum;

public class Grade {
    private long id;
    private GradeEnum grade;


    public Grade() {
    }

    public Grade(GradeEnum grade) {
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GradeEnum getGrade() {
        return grade;
    }

    public void setGrade(GradeEnum grade) {
        this.grade = grade;
    }



    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +

                '}';
    }
}
