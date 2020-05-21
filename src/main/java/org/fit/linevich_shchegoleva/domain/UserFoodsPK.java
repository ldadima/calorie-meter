package org.fit.linevich_shchegoleva.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
class UserFoodsPK implements Serializable {
    @Id
    @Column(name = "user_login")
    private String userLogin;
    @Id
    @Column(name = "food")
    private Integer food;
}
