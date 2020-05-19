package org.fit.linevich_shchegoleva.services;

import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.mapper.FoodMapper;
import org.fit.linevich_shchegoleva.model.Food;
import org.fit.linevich_shchegoleva.repos.FoodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public Food findById(int id){
        return foodRepository.findById(id).map(
                foodMapper::toFood).orElse(null);
    }

    public List<Food> findAll(Pageable pageable){
        Page<FoodEntity> foodEntities = foodRepository.findAll(pageable);
        return foodMapper.toFoodList(foodEntities.toList());
    }

    public void addFood(Food newFood){
        foodRepository.save(foodMapper.toFoodEntity(newFood));
    }
}
