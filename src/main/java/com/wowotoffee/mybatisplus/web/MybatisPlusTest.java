package com.wowotoffee.mybatisplus.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wowotoffee.mybatisplus.entity.Permission;
import com.wowotoffee.mybatisplus.entity.User;
import com.wowotoffee.mybatisplus.service.IPermissionService;
import com.wowotoffee.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("")
public class MybatisPlusTest {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService iPermissionService;

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

}
