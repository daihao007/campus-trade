package com.campustrade.backend.controller;

import com.campustrade.backend.entity.User;
import com.campustrade.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/list")
    public List<User> list(){
        return userService.list();
    }
}
