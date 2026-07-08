package com.campustrade.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campustrade.backend.dto.UserRegisterDTO;
import com.campustrade.backend.entity.User;

public interface UserService extends IService<User>{
    void register(UserRegisterDTO registerDTO);
}
