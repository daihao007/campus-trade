package com.campustrade.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campustrade.backend.dto.UserRegisterDTO;
import com.campustrade.backend.entity.User;
import com.campustrade.backend.dto.UserLoginDTO;

public interface UserService extends IService<User>{
    void register(UserRegisterDTO registerDTO);

    User login(UserLoginDTO loginDTO);
}
