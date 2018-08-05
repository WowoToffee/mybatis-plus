package com.wowotoffee.mybatisplus.web;


import com.wowotoffee.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-03
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @GetMapping("/listuser")
    public String userList(Map<String,Object> map){
        map.put("users",iUserService.selectList(null));
        return "listUser";
    }

    @PostMapping("/delectuser")
    public String delectUser(int id){
        iUserService.deleteById(id);
        return "listUser";
    }
}

