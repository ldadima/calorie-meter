package org.fit.linevich_shchegoleva.model;

import lombok.Getter;
import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.views.Food;

import java.util.Optional;

@Getter
public enum FoodTest {
    FOUND(1),
    NOT_FOUND(0);

    private final int id;
    private Optional<FoodEntity> actual;
    private Food expected;

    FoodTest(int id) {
        this.id = id;
        switch (id){
            case 1:{
                FoodEntity foodEntity = new FoodEntity();
                foodEntity.setId(1);
                foodEntity.setName("1");
                foodEntity.setWeight(1);
                foodEntity.setCalories(1);
                actual = Optional.of(foodEntity);
                expected = new Food();
                expected.setId(1);
                expected.setName("1");
                expected.setWeight(1);
                expected.setCalories(1);
                break;
            }
            case 0:{
                actual = Optional.empty();
                expected = null;
            }
        }
        
    }
}
