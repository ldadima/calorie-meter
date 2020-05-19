package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class FoodCW {
    @NotNull
    private Food food;
    @NotNull
    private Integer weight;
}
