package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fit.linevich_shchegoleva.model.food.CalorieLevel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private Integer id;
    @NotNull(message = "Название должно быть выбрано")
    private String name;
    @NotNull
    @Min(value = 0, message = "Калорий должно быть больше 0")
    private Integer calories;
    private CalorieLevel level;
}
