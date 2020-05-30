package org.fit.linevich_shchegoleva.services;

import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.mapper.DataMapper;
import org.fit.linevich_shchegoleva.model.Food;
import org.fit.linevich_shchegoleva.repos.FoodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final DataMapper dataMapper;

    public Food findById(int id){
        return foodRepository.findById(id).map(
                dataMapper::toFood).orElse(null);
    }

    public Page<Food> findAll(int page, int size){
        Page<FoodEntity> foodEntities = foodRepository.findAll(PageRequest.of(
                page, size, Sort.by("name").ascending()
        ));
        return dataMapper.toFoodPage(foodEntities);
    }

    public boolean addFood(Food newFood){
        Optional<FoodEntity> foodEntity = foodRepository.findByNameAndCalories(newFood.getName(), newFood.getCalories());
        if(foodEntity.isPresent()){
            return false;
        }
        foodRepository.save(dataMapper.toFoodEntity(newFood));
        return true;
    }

    public Page<Food> foodContainsString(int page, int size, String subName) {
        Page<FoodEntity> foodEntities = foodRepository.findAllByNameContainingIgnoreCase(subName,PageRequest.of(
                page, size, Sort.by("name").ascending()
        ));
        return dataMapper.toFoodPage(foodEntities);
    }
}
