package org.fit.linevich_shchegoleva.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.model.Food;
import org.fit.linevich_shchegoleva.services.FoodService;
import org.springframework.data.domain.Page;
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

import javax.validation.Valid;

@RestController
@RequestMapping("/calorie-meter/food")
@AllArgsConstructor
@CrossOrigin(origins = "${front-url}")
@Api
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/getFood")
    @ResponseBody
    public ResponseEntity<Food> getFoodById(@RequestParam int id){
        return ResponseEntity.ok(foodService.findById(id));
    }

    @GetMapping("/allFood")
    @ResponseBody
    public ResponseEntity<Page<Food>> getFood(int page, int size){
        return ResponseEntity.ok(foodService.findAll(page, size));
    }

    @GetMapping("/foodContainsString")
    @ResponseBody
    public ResponseEntity<Page<Food>> foodContainsString(int page, int size, String subName){
        return ResponseEntity.ok(foodService.foodContainsString(page, size, subName));
    }

    @PostMapping("/newFood")
    public ResponseEntity<String> addFood(@RequestBody @Valid Food newFood){
        boolean answer = foodService.addFood(newFood);
        if (answer) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Successful adding food");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Такая еда уже существует");
        }
    }
}
