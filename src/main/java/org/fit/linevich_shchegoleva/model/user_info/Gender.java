package org.fit.linevich_shchegoleva.model.user_info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("М"),
    FEMALE("Ж");

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
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return gender;
    }
}
