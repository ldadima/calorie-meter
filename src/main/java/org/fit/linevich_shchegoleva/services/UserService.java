package org.fit.linevich_shchegoleva.services;

import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.fit.linevich_shchegoleva.domain.UserEntity;
import org.fit.linevich_shchegoleva.domain.UserFoodsEntity;
import org.fit.linevich_shchegoleva.domain.UserFoodsPK;
import org.fit.linevich_shchegoleva.mapper.DataMapper;
import org.fit.linevich_shchegoleva.model.FoodCW;
import org.fit.linevich_shchegoleva.model.ListUserFood;
import org.fit.linevich_shchegoleva.model.User;
import org.fit.linevich_shchegoleva.model.UserFood;
import org.fit.linevich_shchegoleva.model.user_info.Gender;
import org.fit.linevich_shchegoleva.repos.FoodRepository;
import org.fit.linevich_shchegoleva.repos.UserRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private static final int MALE_OFFSET = 5;
    private static final int FEMALE_OFFSET = -161;
    private static final float WEIGHT_COEFFICIENT = 10;
    private static final float HEIGHT_COEFFICIENT = 6.25f;
    private static final float AGE_COEFFICIENT = 5;
    private final UserRepository userRepository;
    private final DataMapper dataMapper;
    private final EntityManager entityManager;
    private final CalorieService calorieService;
    private final FoodRepository foodRepository;


    private User findByLogin(String login) {
        return userRepository.findById(login).map(
                dataMapper::toUser).orElse(null);
    }

    public boolean saveUser(User user) {
        if (findByLogin(user.getLogin()) != null) {
            return false;
        }
        userRepository.save(dataMapper.toUserEntity(user));
        return true;
    }

    public int login(String login, String password) {
        if (login == null || password == null) {
            return -1;
        }
        User user = findByLogin(login);
        if (user == null) {
            return -1;
        }
        if (user.getPassword().equals(password)) {
            return 0;
        }
        return -2;
    }

    public boolean updateUser(User user) {
        UserEntity userEntity = userRepository.findById(user.getLogin()).orElse(null);
        if (userEntity == null) {
            return false;
        }
        if (user.getWeight() != null) {
            userEntity.setWeight(user.getWeight());
        }
        if (user.getHeight() != null) {
            userEntity.setHeight(user.getHeight());
        }
        userRepository.save(userEntity);
        return true;
    }

    public Integer calculateNorm(String login) {
        User info = findByLogin(login);
        if (info == null) {
            return null;
        }
        int offset = 0;
        if (info.getGender() == Gender.MALE) {
            offset = MALE_OFFSET;
        } else{
            offset = FEMALE_OFFSET;
        }
        return Math.round(WEIGHT_COEFFICIENT * info.getWeight() + HEIGHT_COEFFICIENT * info.getHeight() -
                AGE_COEFFICIENT * info.getAge() + offset);
    }

    public ListUserFood getUserFoods(int page, int size, String login) {
        List<FoodCW> foodCWList = entityManager.createQuery(
                "select new org.fit.linevich_shchegoleva.model.FoodCW(f, uf.weight) from UserEntity as u " +
                        "join UserFoodsEntity uf on uf.userLogin = u " +
                        " join FoodEntity as f on f = uf.food " +
                        "where u.login = :login", FoodCW.class)
                .setParameter("login", login)
                .getResultList();
        ArrayList<UserFood> userFoods = new ArrayList<>();
        int allCalories = 0;
        for (FoodCW foodCW : foodCWList) {
            int calories = calorieService.calculateCalories(foodCW.getFood(), foodCW.getWeight());
            allCalories += calories;
            userFoods.add(dataMapper.toUserFood(foodCW, calories));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return new ListUserFood(new PageImpl<UserFood>(userFoods, pageable, userFoods.size()), allCalories);
    }

    public boolean addFood(String login, int foodId, int weight) {
        UserEntity userEntity = userRepository.findById(login).orElse(null);
        FoodEntity foodEntity = foodRepository.findById(foodId).orElse(null);
        if (userEntity == null || foodEntity == null)
            return false;
        userEntity.addFood(new UserFoodsEntity(new UserFoodsPK(login, foodId), userEntity, foodEntity, weight));
        userRepository.save(userEntity);
        return true;
    }

    @Transactional
    public boolean deleteFood(String login, int foodId) {
        UserEntity userEntity = userRepository.findById(login).orElse(null);
        if (userEntity == null)
            return false;
        userEntity.deleteFood(foodId);
        userRepository.save(userEntity);
        return true;
    }

    @Transactional
    public boolean deleteAllFood(String login) {
        UserEntity userEntity = userRepository.findById(login).orElse(null);
        if (userEntity == null)
            return false;
        userEntity.deleteAllFood();
        userRepository.save(userEntity);
        return true;
    }
}
