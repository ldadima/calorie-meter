package org.fit.linevich_shchegoleva.mapper;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.model.Food;
import org.fit.linevich_shchegoleva.model.FoodTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataMapperTest {

    private final DataMapper dataMapper = Mappers.getMapper(DataMapper.class);

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void toFood(FoodTest test) {
        Food actual = dataMapper.toFood(test.getActual().orElse(null));
        assertEquals(test.getExpected(), actual);
    }

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void toFoodEntity(FoodTest test) {
        FoodEntity actual = dataMapper.toFoodEntity(test.getExpected());
        assertEquals(test.getActual().orElse(null), actual);
    }

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void toFoodList(FoodTest test) {
        List<Food> actual;
        List<Food> expected;
        if (test.getActual().isPresent()) {
            actual = dataMapper.toFoodList(List.of(test.getActual().get()));
            expected = List.of(test.getExpected());
        } else {
            actual = dataMapper.toFoodList(List.of());
            expected = List.of();
        }
        assertEquals(expected, actual);
    }
}