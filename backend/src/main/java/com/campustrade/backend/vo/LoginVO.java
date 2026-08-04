package com.campustrade.backend.vo;

import lombok.Data;

@Data
public class LoginVO {
    
    private Long id;

    private String username;

    private String nickname;

    private String avatar;

    private Integer role;

    private String token;
}
