package org.fit.linevich_shchegoleva.model;

import lombok.Getter;
import org.fit.linevich_shchegoleva.model.user_info.Gender;

@Getter
public enum CalculateNormTest {
    NULL(null, null),
    MALE(new UserInfo(Gender.MALE, 32, 80, 175), 1739),
    FEMALE(new UserInfo(Gender.FEMALE, 32, 80, 175), 1573);

    private final UserInfo info;
    private final Integer norm;

    CalculateNormTest(UserInfo info, Integer norm) {
        this.info = info;
        this.norm = norm;
    }
}
