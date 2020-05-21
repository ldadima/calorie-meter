package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.mapper.DataMapper;
import org.fit.linevich_shchegoleva.repos.FoodRepository;
import org.fit.linevich_shchegoleva.model.FoodTest;
import org.fit.linevich_shchegoleva.model.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class FoodServiceTest {
    @Mock
    private FoodRepository foodRepository;
    @Mock
    private DataMapper dataMapper;
    @InjectMocks
    private FoodService foodService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setCalories(100);
        foodEntity.setId(100);
        foodEntity.setName("Гречка");
        Food foodShould = new Food();
        foodShould.setCalories(100);
        foodShould.setId(100);
        foodShould.setName("Гречка");
        Pageable pageable = PageRequest.of(
                0, 20, Sort.by("name").ascending()
        );
        Page<FoodEntity> actual = new PageImpl<>(List.of(foodEntity));
        Page<Food> should = new PageImpl<>(List.of(foodShould));
        when(foodRepository.findAll(pageable)).thenReturn(actual);
        when(dataMapper.toFoodPage(actual)).thenReturn(should);
        Page<Food> actualFoods = foodService.findAll(pageable.getPageNumber(),pageable.getPageSize());
        assertEquals(should, actualFoods);
    }

    @ParameterizedTest
    @EnumSource(FoodTest.class)
    void findById(FoodTest test) {
        when(foodRepository.findById(test.getId())).thenReturn(test.getActual());
        if (test.getActual().isPresent()) {
            when(dataMapper.toFood(test.getActual().get())).thenReturn(test.getExpected());
        }
        Food actual = foodService.findById(test.getId());
        assertEquals(test.getExpected(), actual);
    }

    @Test
    void addFood(){
        Food food = FoodTest.FOUND.getExpected();
        FoodEntity foodEntity = FoodTest.FOUND.getActual().get();
        when(dataMapper.toFoodEntity(food)).thenReturn(foodEntity);
        foodService.addFood(food);
        verify(foodRepository).save(foodEntity);
    }

}