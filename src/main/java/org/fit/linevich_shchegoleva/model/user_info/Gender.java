package org.fit.linevich_shchegoleva.model.user_info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @JsonCreator
    public static Gender findByCode(String code){
        for(Gender one: values()){
            if(code.equals(one.gender))
                return one;
        }
        throw new IllegalArgumentException(String.format("Error code for gender: %s", code));
    }

    @Override
    @JsonValue
    public String toString() {
        return gender;
    }
}
