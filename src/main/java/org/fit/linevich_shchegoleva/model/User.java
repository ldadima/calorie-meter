package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fit.linevich_shchegoleva.model.user_info.Gender;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull(message = "Логин должен быть введен")
    private String login;
    @NotNull(message = "Пароль должен быть введен")
    private String password;
    @NotNull(message = "Дата рождения должна быть выбрана")
    @Past(message = "Дата рождения должна быть раньше текущей")
    private LocalDate birthday;
    private Integer age;
    @Min(value = 1, message = "Вес должен быть больше 0")
    private Integer weight;
    @Min(value = 1, message = "Рост должен быть больше 0")
    private Integer height;
    @NotNull(message = "Пол должен быть выбран")
    private Gender gender;
}
