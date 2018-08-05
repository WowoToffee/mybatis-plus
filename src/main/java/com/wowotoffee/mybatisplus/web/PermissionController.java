package com.wowotoffee.mybatisplus.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wowotoffee.mybatisplus.entity.Permission;
import com.wowotoffee.mybatisplus.entity.RolePermission;
import com.wowotoffee.mybatisplus.service.IPermissionService;
import com.wowotoffee.mybatisplus.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRolePermissionService rolePermissionService;

    /**
     * 首页
     * @return
     */
    @GetMapping("/permissionIndex")
    public String permissionIndex(){
        return "loginPermission";
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @PostMapping("/addPermission")
    public String addPermission(Permission permission){
        permissionService.insert(permission);
        return "loginPermission";
    }

    /**
     * 修改权限
     */
    @PostMapping("/updatePermission")
    public String updatePermission(Permission permission){
        permissionService.updateById(permission);
        return "loginPermission";
    }

    /**
     * 查询所有的权限
     * @return
     */
    @PostMapping("/selectPermission")
    public Page<Permission> selectPermission(int current,int size){
        Page<Permission> permissions =  permissionService.selectPage(new Page(current,size),null);
        return permissions;
    }
    @PostMapping("/deletePermission")
    public String deletePermission(int id){
        permissionService.deleteById(id);
        rolePermissionService.delete(new EntityWrapper<RolePermission>()
                .eq("pid",id));
        return "loginPermission";
    }
}

