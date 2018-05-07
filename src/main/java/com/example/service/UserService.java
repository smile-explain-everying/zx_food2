package com.example.service;

import com.example.bean.User;
import com.example.dao.mapper.UserMapper;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUsers(String username,String password) {
      /*  User user = new User();
        user.setPassword(password);
        user.setUsername(username);*/
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("username",username);
        param.put("password",password);
        return userMapper.selectModel(param);

    }
}