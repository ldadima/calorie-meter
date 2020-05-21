package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.fit.linevich_shchegoleva.domain.FoodEntity;

@Data
@AllArgsConstructor
public class FoodCW {
    private FoodEntity food;
    private Integer weight;
}
