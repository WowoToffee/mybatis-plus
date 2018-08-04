package com.wowotoffee.mybatisplus.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wowotoffee.mybatisplus.entity.Permission;
import com.wowotoffee.mybatisplus.entity.Role;
import com.wowotoffee.mybatisplus.entity.UserRole;
import com.wowotoffee.mybatisplus.mapper.PermissionMapper;
import com.wowotoffee.mybatisplus.mapper.RoleMapper;
import com.wowotoffee.mybatisplus.mapper.UserMapper;
import com.wowotoffee.mybatisplus.mapper.UserRoleMapper;
import com.wowotoffee.mybatisplus.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wowotoffee.mybatisplus.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-03
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Set<String> listPermissionURLs(String userName) {
        Set<String> result = new HashSet<>();
        //根据username来查找permission的权限
        List<Permission> permissions = roleMapper.selectPermissionByUsername(userName);
        for (Permission permission : permissions) {
            result.add(permission.getUrl());
        }
        return result;
    }

    @Override
    public Set<String> listPermissionNames(String userName) {
        Set<String> result = new HashSet<>();
        //根据username来查找permission的权限
        List<Permission> permissions = roleMapper.selectPermissionByUsername(userName);
        for (Permission permission : permissions) {
            result.add(permission.getName());
        }
        return result;
    }

    @Override
    public List<Permission> list() {

        return null;
    }

    @Override
    public boolean needInterceptor(String requestURI) {
        List<Permission> ps = permissionMapper.selectList(new EntityWrapper<Permission>());
        for (Permission p : ps) {
            if (p.getUrl().equals(requestURI))
                return true;
        }
        return false;
    }
}
