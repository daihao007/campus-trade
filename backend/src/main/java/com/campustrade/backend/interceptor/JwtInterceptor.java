package com.campustrade.backend.interceptor;

import com.campustrade.backend.BackendApplication;
import com.campustrade.backend.context.UserContext;
import com.campustrade.backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.campustrade.backend.context.UserContext;


@Component
public class JwtInterceptor implements HandlerInterceptor{

    private final BackendApplication backendApplication;

    JwtInterceptor(BackendApplication backendApplication) {
        this.backendApplication = backendApplication;
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        String token = request.getHeader("Authorization");

        //没有token
        if(token==null || !token.startsWith("Bearer ")){
            response.setStatus(401);
            return false;
        }
        //去掉Bearer前缀
        token = token.substring(7);

        try{
            //解析token
            Long userId = JwtUtil.getUserId(token);

            //保存用户id
            UserContext.setUserId(userId);
        } catch (Exception e){
            response.setStatus(401);
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ){
        UserContext.remove();
    }
}
