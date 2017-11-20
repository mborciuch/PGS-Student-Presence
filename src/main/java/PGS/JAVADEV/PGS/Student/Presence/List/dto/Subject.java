package PGS.JAVADEV.PGS.Student.Presence.List.dto;

public class Subject {

    private Long id;
    private String name;
    private String lecturer;

    public Subject() {
    }

    public Subject(String name, String lecturer) {
        this.name = name;
        this.lecturer = lecturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}
