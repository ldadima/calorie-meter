package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.model.FoodCW;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalorieService {

    public int calculateCalories(List<FoodCW> foods){
        float calories = 0;
        for(FoodCW food: foods){
            calories = calories + food.getWeight() * food.getFood().getCalories()*0.01f;
        }
        return Math.round(calories);
    }
}
