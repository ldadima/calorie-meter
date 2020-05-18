package org.fit.linevich_shchegoleva.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.services.FoodService;
import org.fit.linevich_shchegoleva.views.Food;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calorie-meter")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Api
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/food")
    @ResponseBody
    public Food getFoodById(@RequestParam int id){
        return foodService.findById(id);
    }

    @GetMapping("/allFood")
    @ResponseBody
    public List<Food> getFood(){
        return foodService.findAll();
    }

    @PostMapping("/newFood")
    public ResponseEntity<String> addFood(@RequestBody Food newFood){
        foodService.addFood(newFood);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful adding food\n");
    }

}
