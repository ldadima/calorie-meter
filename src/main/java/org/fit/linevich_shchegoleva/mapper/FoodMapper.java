package org.fit.linevich_shchegoleva.mapper;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.model.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FoodMapper {
    Food toFood(FoodEntity foodEntity);
    @Mapping(ignore = true, target = "level")
    FoodEntity toFoodEntity(Food food);
    List<Food> toFoodList(Iterable<FoodEntity> foodEntity);
}
