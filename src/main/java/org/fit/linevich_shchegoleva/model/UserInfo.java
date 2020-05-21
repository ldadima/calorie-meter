package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.fit.linevich_shchegoleva.model.user_info.Gender;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserInfo {
    @NotNull
    private Gender gender;
    @NotNull
    private Integer age;
    @NotNull
    private Integer weight;
    @NotNull
    private Integer height;
}
