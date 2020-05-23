package org.fit.linevich_shchegoleva.mapper;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.domain.UserEntity;
import org.fit.linevich_shchegoleva.model.Food;
import org.fit.linevich_shchegoleva.model.FoodCW;
import org.fit.linevich_shchegoleva.model.User;
import org.fit.linevich_shchegoleva.model.UserFood;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public abstract class DataMapper {
    public abstract Food toFood(FoodEntity foodEntity);
    @Mapping(ignore = true, target = "level")
    @Mapping(ignore = true, target = "userFoodsEntities")
    public abstract FoodEntity toFoodEntity(Food food);
    public abstract List<Food> toFoodList(Iterable<FoodEntity> foodEntity);
    public Page<Food> toFoodPage(Page<FoodEntity> foodEntity){
        return new PageImpl<Food>(toFoodList(foodEntity.toList()), foodEntity.getPageable(), foodEntity.getTotalElements());
    }

    public abstract User toUser(UserEntity userEntity);
    @Mapping(ignore = true, target = "userFoodsEntities")
    public abstract UserEntity toUserEntity(User user);
    public abstract List<User> toUserList(List<UserEntity> userEntities);

    @Mapping(source = "foodCW.food.name", target = "name")
    @Mapping(source = "foodCW.food.id", target = "id")
    @Mapping(source = "foodCW.weight", target = "weight")
    @Mapping(source = "calories", target = "calories")
    public abstract UserFood toUserFood(FoodCW foodCW, int calories);
}
