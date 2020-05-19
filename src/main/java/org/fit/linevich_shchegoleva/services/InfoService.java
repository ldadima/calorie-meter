package org.fit.linevich_shchegoleva.services;

import io.swagger.models.auth.In;
import org.fit.linevich_shchegoleva.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    private final int MALE_OFFSET = 5;
    private final int FEMALE_OFFSET = -161;
    private final float WEIGHT_COEFFICIENT = 10;
    private final float HEIGHT_COEFFICIENT = 6.25f;
    private final float AGE_COEFFICIENT = 5;

    public Integer calculateNorm(UserInfo info) {
        if(info == null){
            return null;
        }
        int offset = 0;
        switch (info.getGender()) {
            case MALE: {
                offset = MALE_OFFSET;
                break;
            }
            case FEMALE: {
                offset = FEMALE_OFFSET;
                break;
            }
        }
        int norm = Math.round(WEIGHT_COEFFICIENT * info.getWeight() + HEIGHT_COEFFICIENT * info.getHeight() -
                AGE_COEFFICIENT * info.getAge() + offset);
        return norm;
    }
}
