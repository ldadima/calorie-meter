package org.fit.linevich_shchegoleva.mapper;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.domain.UserEntity;
import org.fit.linevich_shchegoleva.model.Food;
import org.fit.linevich_shchegoleva.model.FoodCW;
import org.fit.linevich_shchegoleva.model.FoodTest;
import org.fit.linevich_shchegoleva.model.User;
import org.fit.linevich_shchegoleva.model.UserFood;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.fit.linevich_shchegoleva.services.UserServiceTest.getUser;
import static org.fit.linevich_shchegoleva.services.UserServiceTest.getUserEntity;
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

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void toFoodPage(FoodTest test) {
        Page<Food> actual;
        List<Food> expected;
        if (test.getActual().isPresent()) {
            actual = dataMapper.toFoodPage(new PageImpl<>(List.of(test.getActual().get())));
            expected = List.of(test.getExpected());
        } else {
            actual = dataMapper.toFoodPage(new PageImpl<>(List.of()));
            expected = List.of();
        }
        assertEquals(expected, actual.toList());
    }

    @Test
    void toUser() {
        UserEntity userEntity = getUserEntity();
        User userExpected = getUser();
        User actual = dataMapper.toUser(userEntity);
        assertEquals(userExpected, actual);
    }

    @Test
    void toUserEntity() {
        UserEntity expected = getUserEntity();
        expected.setUserFoodsEntities(null);
        User user = getUser();
        UserEntity actual = dataMapper.toUserEntity(user);
        assertEquals(expected, actual);
    }

    @Test
    void toUserFood() {
        FoodEntity foodEntity = FoodTest.FOUND.getActual().get();
        FoodCW foodCW = new FoodCW(foodEntity, 100);
        UserFood expected = new UserFood(foodEntity.getId(), foodEntity.getName(), 100, 1000);
        UserFood actual = dataMapper.toUserFood(foodCW,1000);
        assertEquals(expected, actual);
    }
}