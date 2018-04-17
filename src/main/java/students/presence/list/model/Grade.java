package students.presence.list.model;

import javax.validation.constraints.NotNull;

@NotNull
public enum Grade {
    A("5"), B("4"), C("3"), D("2"), E("1");
    private final String shortCode;

    Grade(String code) {
        this.shortCode = code;
    }
}
