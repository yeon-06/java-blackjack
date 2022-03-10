package domain.participant;

import java.util.Objects;

public class Name {
    private static final String EMPTY_NAME_ERROR = "[ERROR] 빈 값은 이름으로 등록할 수 없습니다.";

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}