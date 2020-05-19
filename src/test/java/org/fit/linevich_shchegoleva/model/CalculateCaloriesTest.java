package org.fit.linevich_shchegoleva.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum CalculateCaloriesTest {
    EMPTY(List.of(), 0),
    NOT_EMPTY(List.of(new FoodCW(new Food(null, "", 100, null), 130),
            new FoodCW(new Food(null, "", 120, null), 50),
            new FoodCW(new Food(null, "", 192, null), 420)), 996);

    private final List<FoodCW> foods;
    private final int calories;

    CalculateCaloriesTest(List<FoodCW> foods, int calories) {
        this.foods = foods;
        this.calories = calories;
    }
}
