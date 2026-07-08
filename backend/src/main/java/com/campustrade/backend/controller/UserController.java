package com.campustrade.backend.controller;

import com.campustrade.backend.entity.User;
import com.campustrade.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campustrade.backend.common.Result;
import com.campustrade.backend.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/list")
    public Result<List<User>> list(){
        return Result.success(userService.list());
    }

    @PostMapping("/user/register")
    public Result<Void> register(@Valid @RequestBody UserRegisterDTO registerDTO){
        userService.register(registerDTO);
        return Result.success();
    }
}
