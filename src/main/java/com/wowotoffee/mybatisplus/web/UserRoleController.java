package com.wowotoffee.mybatisplus.web;


import com.wowotoffee.mybatisplus.entity.UserRole;
import com.wowotoffee.mybatisplus.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-03
 */
@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private IUserRoleService iUserRoleService;
    //给用户授予角色
    @PostMapping("/addUserRole")
    public String addUserRole(UserRole userRole){
        iUserRoleService.insert(userRole);
        return "listUser";
    }

    @GetMapping("/selectUserRole")
    @ResponseBody
    public List<UserRole> selectUserRole(){
        return iUserRoleService.selectList(null);
    }


}

