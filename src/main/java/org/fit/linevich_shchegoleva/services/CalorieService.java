package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.springframework.stereotype.Service;

@Service
public class CalorieService {

    public int calculateCalories(FoodEntity food, int weight){
        float calories = 0;
        if(food == null){
            return 0;
        }
        calories = calories + weight * food.getCalories()*0.01f;
        return Math.round(calories);
    }
}
