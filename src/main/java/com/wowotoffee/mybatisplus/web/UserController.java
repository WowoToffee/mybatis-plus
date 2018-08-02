package com.wowotoffee.mybatisplus.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wowotoffee.mybatisplus.entity.User;
import com.wowotoffee.mybatisplus.service.IUserService;
import com.wowotoffee.mybatisplus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/seletpage")
    public Page<User> selectPage() {
        //使用条件构造器进行分页查询
        Page<User> users = userService.selectPage(new Page(1,2),new EntityWrapper<User>()
                .like("user_name","gay"));
        return users;
    }
    /**
     * AR 的一些操作
     */
    @GetMapping("/arPage")
    public Page<User> arPage(){
        User user = new User();
        Page<User> page = user.selectPage(new Page(1, 2), new EntityWrapper<User>()
                .eq("user_name", "gay"));
        return page;
    }

    @GetMapping("/addUser")
    public User addUser(){
        User user = new User();
        userService.insert(user);
        return user;
    }
}

