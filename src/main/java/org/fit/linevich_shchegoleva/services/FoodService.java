package org.fit.linevich_shchegoleva.services;

import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.mapper.FoodMapper;
import org.fit.linevich_shchegoleva.repos.FoodRepository;
import org.fit.linevich_shchegoleva.views.Food;
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

    public List<Food> findAll(){
        return foodMapper.toFoodList(foodRepository.findAll());
    }

    public void addFood(Food newFood){
        foodRepository.save(foodMapper.toFoodEntity(newFood));
    }
}
