package org.fit.linevich_shchegoleva.mapper;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.views.Food;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FoodMapper {
    Food toFood(FoodEntity foodEntity);
    FoodEntity toFoodEntity(Food food);
    List<Food> toFoodList(Iterable<FoodEntity> foodEntity);

}
