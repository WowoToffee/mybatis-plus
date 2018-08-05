package com.wowotoffee.mybatisplus.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wowotoffee.mybatisplus.entity.RolePermission;
import com.wowotoffee.mybatisplus.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-03
 */
@Controller
@RequestMapping("/rolePermission")
public class RolePermissionController {
    @Autowired
    private IRolePermissionService rolePermissionService;

    /**
     * 首页
     * @return
     */
    @GetMapping("/rolePermissionIndex")
    public String rolePermissionIndex(){
        return "loginRolePermission";
    }

    @PostMapping("/addRolePermission")
    public String addRolePermission(RolePermission rolePermission){

        rolePermissionService.insert(rolePermission);
        return "loginRolePermission";
    }
    /**
     * 修改权限
     */
    @PostMapping("/updateRolePermission")
    public String updatePermission(RolePermission rolePermission){
        rolePermissionService.updateById(rolePermission);
        return "loginRolePermission";
    }

    /**
     * 查询所有的权限
     * @return
     */
    @PostMapping ("/selectRolePermission")
    @ResponseBody
    public Page<RolePermission> selectRolePermission(c){
        Page<RolePermission> rolePermissions =  rolePermissionService.selectPage(new Page(current,size),null);
        return rolePermissions;
    }

    /**
     * 删除该角色权限
     * @param id
     * @return
     */
    @PostMapping("/deleteRolePermission")
    public String deleteRolePermission(int id){
        rolePermissionService.deleteById(id);

        return "loginPermission";
    }

}

