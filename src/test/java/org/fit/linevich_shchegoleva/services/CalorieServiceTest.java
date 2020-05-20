package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.model.CalculateCaloriesTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalorieServiceTest {
    private final CalorieService service = new CalorieService();

    @ParameterizedTest
    @EnumSource(CalculateCaloriesTest.class)
    void calculateCalories(CalculateCaloriesTest test) {
        Integer calories = service.calculateCalories(test.getFood(), test.getWeight());
        assertEquals(test.getCalories(), calories);
    }
}