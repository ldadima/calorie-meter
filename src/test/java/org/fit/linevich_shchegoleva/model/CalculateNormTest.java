package org.fit.linevich_shchegoleva.model;

import lombok.Getter;
import org.fit.linevich_shchegoleva.model.User;
import org.fit.linevich_shchegoleva.model.user_info.Gender;

import java.sql.Date;
import java.time.LocalDate;

@Getter
public enum CalculateNormTest {
    NULL(null, null),
    MALE(new User( "kek", "kek", null, 32, 80, 175, Gender.MALE), 1739),
    FEMALE(new User( "kek", "kek", null, 32, 80, 175, Gender.FEMALE), 1573);

    private final User info;
    private final Integer norm;

    CalculateNormTest(User info, Integer norm) {
        this.info = info;
        this.norm = norm;
    }
}
