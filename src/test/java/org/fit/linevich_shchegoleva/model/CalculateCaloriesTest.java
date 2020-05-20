package org.fit.linevich_shchegoleva.model;

import lombok.Getter;
import org.fit.linevich_shchegoleva.domain.FoodEntity;

@Getter
public enum CalculateCaloriesTest {
    EMPTY(null, 0, 0),
    NOT_EMPTY(new FoodEntity(null, "dd", 100, null, null), 150, 150);

    private final FoodEntity food;
    private final int weight;
    private final int calories;

    CalculateCaloriesTest(FoodEntity food, int weight, int calories) {
        this.food = food;
        this.weight = weight;
        this.calories = calories;
    }
}
