package com.campustrade.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campustrade.backend.entity.User;
import com.campustrade.backend.mapper.UserMapper;
import com.campustrade.backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    
}
