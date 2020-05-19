package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fit.linevich_shchegoleva.model.food.CalorieLevel;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Integer calories;
    private CalorieLevel level;
}
