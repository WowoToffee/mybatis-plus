package com.wowotoffee.mybatisplus.web;

import com.wowotoffee.mybatisplus.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class LoginController {
    @GetMapping("/index")
    public String userIndex(){
        User user = new User();
        //userService.insert(user);
        return "index";
    }
    @GetMapping("/login")
    public String userLogin(){
        User user = new User();
        //userService.insert(user);
        return "login";
    }
    @GetMapping("/unauthorized")
    public String userUnauthorized(){
        User user = new User();
        //userService.insert(user);
        return "unauthorized";
    }
}
