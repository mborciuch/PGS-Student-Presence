package PGS.JAVADEV.PGS.Student.Presence.List.model;

public enum GradeEnum {
    A("5"), B("4"), C("3"), D("2"), E("1");
    private final String shortCode;

    GradeEnum(String code) {
        this.shortCode = code;
    }
}
