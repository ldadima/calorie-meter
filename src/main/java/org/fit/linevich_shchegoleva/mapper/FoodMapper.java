package org.fit.linevich_shchegoleva.mapper;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.views.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FoodMapper {
    @Mapping(source = "level.code", target = "level")
    Food toFood(FoodEntity foodEntity);
    @Mapping(ignore = true, target = "level")
    FoodEntity toFoodEntity(Food food);
    List<Food> toFoodList(Iterable<FoodEntity> foodEntity);

}
