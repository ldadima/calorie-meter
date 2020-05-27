package org.fit.linevich_shchegoleva.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.model.ListUserFood;
import org.fit.linevich_shchegoleva.model.User;
import org.fit.linevich_shchegoleva.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/calorie-meter/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Api
public class UserController {
    private final UserService userService;

    @PutMapping("/changeInfo")
    public ResponseEntity<String> changeInfo(@RequestBody User info) {
        boolean changed = userService.updateUser(info);
        if (changed) {
            return ResponseEntity.ok("Информация обновлена");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PostMapping("/addFood")
    public ResponseEntity<String> addFood(String login, int foodId, int weight) {
        boolean changed = userService.addFood(login, foodId, weight);
        if (changed) {
            return ResponseEntity.ok("Еда добавлена");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найден пользователь или еда");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid User user) {
        boolean changed = userService.saveUser(user);
        if (changed) {
            return ResponseEntity.ok("Пользователь создан успешно");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Логин занят");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        int changed = userService.login(user.getLogin(), user.getPassword());
        if (changed == 0) {
            return ResponseEntity.ok("Вход успешен");
        } else {
            if (changed == -1) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователя с таким логином не существует");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверный пароль");
            }
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Boolean> auth(@RequestBody User user) {
        int changed = userService.login(user.getLogin(), user.getPassword());
        return ResponseEntity.ok(changed == 0);
    }

    @GetMapping("/calculateNorm")
    public ResponseEntity<Integer> calculate(String login) {
        Integer norm = userService.calculateNorm(login);
        if (norm != null) {
            return ResponseEntity.ok(norm);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/userFoods")
    public ResponseEntity<ListUserFood> userFoods(int page, int size, String login) {
        ListUserFood userFoods = userService.getUserFoods(page, size, login);
        return ResponseEntity.ok(userFoods);

    }

    @DeleteMapping("/deleteFood")
    public ResponseEntity<String> deleteFood(String login, int food) {
        boolean changed = userService.deleteFood(login, food);
        if (changed) {
            return ResponseEntity.ok("Еда удалена");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найден пользователь или еда");
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllFood(String login) {
        boolean changed = userService.deleteAllFood(login);
        if (changed) {
            return ResponseEntity.ok("Вся еда удалена");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найден пользователь");
        }
    }
}
