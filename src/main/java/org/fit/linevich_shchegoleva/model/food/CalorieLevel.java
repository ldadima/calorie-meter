package org.fit.linevich_shchegoleva.model.food;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CalorieLevel {
    LOW("Низкая"),
    NORMAl("Умеренная"),
    HIGH("Высокая");

    private final String code;

    CalorieLevel(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
