package com.wowotoffee.mybatisplus.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.wowotoffee.mybatisplus.entity.User;
import com.wowotoffee.mybatisplus.service.IUserService;
import com.wowotoffee.mybatisplus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-01
 */
@RestController
@RequestMapping("/wowotoffee.mybatisplus/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/test")
    public List<User> test() {
        Map<String,Object> map = new HashMap<>();
        map.put("user_name","gay");
        //代码有误
        List<User> users = (List<User>) userService.selectPage(new Page(1,2),null);
        return users;
    }
}

