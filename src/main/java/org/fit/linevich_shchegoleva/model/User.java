package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fit.linevich_shchegoleva.model.user_info.Gender;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Date birthday;
    @NotNull
    private Integer age;
    private Integer weight;
    private Integer height;
    @NotNull
    private Gender gender;
}
