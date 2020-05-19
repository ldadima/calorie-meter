package org.fit.linevich_shchegoleva.mapper;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.model.FoodTest;
import org.fit.linevich_shchegoleva.model.Food;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodMapperTest {

    private final FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void toFood(FoodTest test) {
        Food actual = foodMapper.toFood(test.getActual().orElse(null));
        assertEquals(test.getExpected(), actual);
    }

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void toFoodEntity(FoodTest test) {
        FoodEntity actual = foodMapper.toFoodEntity(test.getExpected());
        assertEquals(test.getActual().orElse(null), actual);
    }

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void toFoodList(FoodTest test) {
        List<Food> actual;
        List<Food> expected;
        if (test.getActual().isPresent()) {
            actual = foodMapper.toFoodList(List.of(test.getActual().get()));
            expected = List.of(test.getExpected());
        } else {
            actual = foodMapper.toFoodList(List.of());
            expected = List.of();
        }
        assertEquals(expected, actual);
    }
}