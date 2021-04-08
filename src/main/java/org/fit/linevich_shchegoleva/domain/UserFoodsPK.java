package org.fit.linevich_shchegoleva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserFoodsPK implements Serializable {
    private String userLogin;
    private Integer food;
}
