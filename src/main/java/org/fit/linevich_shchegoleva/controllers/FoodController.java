package org.fit.linevich_shchegoleva.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.model.Food;
import org.fit.linevich_shchegoleva.model.FoodCW;
import org.fit.linevich_shchegoleva.services.CalorieService;
import org.fit.linevich_shchegoleva.services.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestController
@RequestMapping("/calorie-meter")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Api
public class FoodController {
    private final FoodService foodService;
    private final CalorieService calorieService;

    @GetMapping("/food")
    @ResponseBody
    public ResponseEntity<Food> getFoodById(@RequestParam int id){
        return ResponseEntity.ok(foodService.findById(id));
    }

    @GetMapping("/allFood")
    @ResponseBody
    public ResponseEntity<List<Food>> getFood(Pageable pageable){
        return ResponseEntity.ok(foodService.findAll(pageable));
    }

    @PostMapping("/newFood")
    public ResponseEntity<String> addFood(@RequestBody Food newFood){
        foodService.addFood(newFood);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful adding food\n");
    }

    @PutMapping("/calculateCalories")
    public ResponseEntity<Integer> addFood(@RequestBody List<FoodCW> foods){
        int calories = calorieService.calculateCalories(foods);
        return ResponseEntity.ok(calories);
    }

}
