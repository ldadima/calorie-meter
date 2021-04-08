package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.domain.UserEntity;
import org.fit.linevich_shchegoleva.domain.UserFoodsEntity;
import org.fit.linevich_shchegoleva.domain.UserFoodsPK;
import org.fit.linevich_shchegoleva.mapper.DataMapper;
import org.fit.linevich_shchegoleva.model.FoodCW;
import org.fit.linevich_shchegoleva.model.FoodTest;
import org.fit.linevich_shchegoleva.model.ListUserFood;
import org.fit.linevich_shchegoleva.model.User;
import org.fit.linevich_shchegoleva.model.UserFood;
import org.fit.linevich_shchegoleva.model.user_info.Gender;
import org.fit.linevich_shchegoleva.repos.FoodRepository;
import org.fit.linevich_shchegoleva.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    public static UserEntity getUserEntity(){
        return new UserEntity("test","test",null,20,20,20, Gender.MALE, new ArrayList<>());
    }

    public static User getUser(){
        return new User("test","test", null,20,20,20, Gender.MALE);
    }

    @Mock
    private UserRepository userRepository;
    @Mock
    private FoodRepository foodRepository;
    @Mock
    private DataMapper dataMapper;
    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<FoodCW> query;
    @Mock
    private CalorieService calorieService;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateNorm() {
        UserEntity userEntity = getUserEntity();
        User user = getUser();
        when(userRepository.findById(userEntity.getLogin())).thenReturn(Optional.of(userEntity));
        when(dataMapper.toUser(userEntity)).thenReturn(user);
        Integer norm = service.calculateNorm(userEntity.getLogin());
        assertEquals(230, norm);
        user.setGender(Gender.FEMALE);
        when(dataMapper.toUser(userEntity)).thenReturn(user);
        norm = service.calculateNorm(userEntity.getLogin());
        assertEquals(64, norm);
        when(userRepository.findById(userEntity.getLogin())).thenReturn(Optional.empty());
        when(dataMapper.toUser(null)).thenReturn(null);
        norm = service.calculateNorm(userEntity.getLogin());
        assertNull(norm);
    }

    @Test
    void saveUser() {
        UserEntity userEntity = getUserEntity();
        User user = getUser();
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        when(dataMapper.toUser(userEntity)).thenReturn(user);
        when(dataMapper.toUserEntity(user)).thenReturn(userEntity);
        boolean ret = service.saveUser(user);
        assertFalse(ret);
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.empty());
        when(dataMapper.toUser(null)).thenReturn(null);
        ret = service.saveUser(user);
        verify(userRepository).save(userEntity);
        assertTrue(ret);
    }

    @Test
    void login() {
        UserEntity userEntity = getUserEntity();
        User user = getUser();
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        when(dataMapper.toUser(userEntity)).thenReturn(user);
        int ret = service.login(user.getLogin(), user.getPassword());
        assertEquals(0, ret);
        ret = service.login(user.getLogin(), "no");
        assertEquals(-2, ret);
        when(dataMapper.toUser(userEntity)).thenReturn(null);
        ret = service.login(user.getLogin(), user.getPassword());
        assertEquals(-1, ret);
        user.setLogin(null);
        ret = service.login(user.getLogin(), user.getPassword());
        assertEquals(-1, ret);
        user.setPassword(null);
        ret = service.login(user.getLogin(), user.getPassword());
        assertEquals(-1, ret);
        user.setLogin("test");
        ret = service.login(user.getLogin(), user.getPassword());
        assertEquals(-1, ret);
    }

    @Test
    void updateUser() {
        UserEntity userEntity = getUserEntity();
        User user = getUser();
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        when(dataMapper.toUser(userEntity)).thenReturn(user);
        user.setHeight(100);
        user.setWeight(100);
        assertTrue(service.updateUser(user));
        verify(userRepository).save(userEntity);
        assertEquals(100, userEntity.getHeight());
        assertEquals(100, userEntity.getWeight());
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.empty());
        assertFalse(service.updateUser(user));
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        user.setHeight(null);
        user.setWeight(null);
        assertTrue(service.updateUser(user));
    }

    @Test
    void getUserFoods(){
        FoodEntity foodEntity = FoodTest.FOUND.getActual().get();
        FoodCW foodCW = new FoodCW(foodEntity, 100);
        String login = getUser().getLogin();
        UserFood expectedUserFood = dataMapper.toUserFood(foodCW,1000);
        when(entityManager.createQuery(
                "select new org.fit.linevich_shchegoleva.model.FoodCW(f, uf.weight) from UserEntity as u " +
                        "join UserFoodsEntity uf on uf.userLogin = u " +
                        " join FoodEntity as f on f = uf.food " +
                        "where u.login = :login", FoodCW.class)).thenReturn(query);
        when(query
                .setParameter("login", login)).thenReturn(query);
        when(query
                .getResultList()).thenReturn(List.of(foodCW));
        when(calorieService.calculateCalories(foodCW.getFood(),foodCW.getWeight())).thenReturn(1000);
        when(dataMapper.toUserFood(foodCW,1000)).thenReturn(expectedUserFood);
        ListUserFood actual = service.getUserFoods(1,1,login);
        Pageable pageable = PageRequest.of(1, 1, Sort.by("name").ascending());
        List<UserFood> need = new ArrayList<>();
        need.add(expectedUserFood);
        ListUserFood expected = new ListUserFood(new PageImpl<UserFood>(need, pageable, 1),1000);
        assertEquals(expected,actual);
    }

    @Test
    void addFood() {
        Optional<FoodEntity> foodEntity = FoodTest.FOUND.getActual();
        UserEntity userEntity = getUserEntity();
        User user = getUser();
        String login = user.getLogin();
        int foodId = foodEntity.get().getId();
        int weight = 100;
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        when(foodRepository.findById(foodEntity.get().getId())).thenReturn(foodEntity);
        service.addFood(login,foodId,weight);
        verify(userRepository).save(userEntity);
        when(foodRepository.findById(foodEntity.get().getId())).thenReturn(Optional.empty());
        service.addFood(login,foodId,weight);
        verify(userRepository).save(userEntity);
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.empty());
        service.addFood(login,foodId,weight);
        verify(userRepository).save(userEntity);
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        service.addFood(login,foodId,weight);
        verify(userRepository).save(userEntity);
    }

    @Test
    void deleteFood() {
        Optional<FoodEntity> foodEntity = FoodTest.FOUND.getActual();
        UserEntity userEntity = getUserEntity();
        User user = getUser();
        String login = user.getLogin();
        int foodId = foodEntity.get().getId();
        UserFoodsEntity userFoodsEntity = new UserFoodsEntity(new UserFoodsPK(login, foodId), userEntity, foodEntity.get(), 100);
        userEntity.addFood(userFoodsEntity);
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        assertTrue(service.deleteFood(login,foodId));
        assertTrue(userEntity.getUserFoodsEntities().isEmpty());
        verify(userRepository).save(userEntity);
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.empty());
        assertFalse(service.deleteFood(login,foodId));
    }

    @Test
    void deleteAllFood() {
        UserEntity userEntity = getUserEntity();
        User user = getUser();
        String login = user.getLogin();
        UserFoodsEntity userFoodsEntity = new UserFoodsEntity(new UserFoodsPK(login, 1), userEntity, new FoodEntity(), 100);
        userEntity.addFood(userFoodsEntity);
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.of(userEntity));
        assertTrue(service.deleteAllFood(login));
        assertTrue(userEntity.getUserFoodsEntities().isEmpty());
        verify(userRepository).save(userEntity);
        when(userRepository.findById(user.getLogin())).thenReturn(Optional.empty());
        assertFalse(service.deleteAllFood(login));
    }
}