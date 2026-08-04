package com.campustrade.backend.controller;

import com.campustrade.backend.common.Result;
import com.campustrade.backend.dto.UserLoginDTO;
import com.campustrade.backend.dto.UserRegisterDTO;
import com.campustrade.backend.entity.User;
import com.campustrade.backend.service.UserService;
import com.campustrade.backend.utils.JwtUtil;
import com.campustrade.backend.vo.LoginVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.campustrade.backend.context.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


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

    @PostMapping("/user/login")
    public Result<LoginVO> login(@Valid @RequestBody UserLoginDTO loginDTO){
        User user = userService.login(loginDTO);
        
        LoginVO loginVO = new LoginVO();

        loginVO.setId(user.getId());
        loginVO.setUsername(user.getUsername());
        loginVO.setNickname(user.getNickname());
        loginVO.setAvatar(user.getAvatar());
        loginVO.setRole(user.getRole());
        loginVO.setToken(
                JwtUtil.createToken(user.getId())  
        );
        return Result.success(loginVO);
    }

    @GetMapping("/user/test-token")
    public Result<Long> getMethodName(@RequestParam String token) {
        Long userId=JwtUtil.getUserId(token);

        return Result.success(userId);
    }
    
    //测试UserContext接口
    @GetMapping("/user/current")
    public Result<Long> current(){
        Long userId = UserContext.getUserId();

        return Result.success(userId);
    }
    
}
