package ru.test.carwash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.test.carwash.dto.UserDTO;
import ru.test.carwash.model.User;
import ru.test.carwash.service.UserService;

import java.util.List;

@RestController
@RequestMapping(produces="application/json")
@Tag(name="UserController", description="Управление пользователями")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/registerUser")
    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать пользователя"
    )
    public User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllUsers")
    @Operation(
            summary = "Просмотр пользователей",
            description = "Позволяет получить списов всех пользователей"
    )
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        return e.getMessage();
    }
}
