package org.fit.linevich_shchegoleva.model;

import org.fit.linevich_shchegoleva.domain.UserEntity;
import org.fit.linevich_shchegoleva.model.user_info.Gender;

import java.util.ArrayList;
import java.util.Collections;

public class UserTest {

    public static UserEntity getUserEntity(){
        return new UserEntity("kek","kek",null,20,20,20, Gender.MALE, new ArrayList<>());
    }

    public static User getUser(){
        return new User("kek","kek", null,20,20,20, Gender.MALE);
    }

}
