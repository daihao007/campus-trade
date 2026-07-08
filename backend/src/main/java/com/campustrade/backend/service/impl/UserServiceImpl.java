package com.campustrade.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campustrade.backend.dto.UserRegisterDTO;
import com.campustrade.backend.entity.User;
import com.campustrade.backend.mapper.UserMapper;
import com.campustrade.backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Override
    public void register(UserRegisterDTO registerDTO){
        Long count = this.lambdaQuery()
                .eq(User::getUsername,registerDTO.getUsername())
                .count();
        if (count>0) {
            throw new RuntimeException("用户名已存在");
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setNickname(registerDTO.getNickname());
        user.setRole(0);

        this.save(user);
    }
}
