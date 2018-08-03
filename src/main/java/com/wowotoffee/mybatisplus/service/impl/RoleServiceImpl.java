package com.wowotoffee.mybatisplus.service.impl;

import com.wowotoffee.mybatisplus.entity.Role;
import com.wowotoffee.mybatisplus.mapper.RoleMapper;
import com.wowotoffee.mybatisplus.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Set<String> selectNameByUsernameService(String username) {
        Set<String> reuls =new HashSet<String>();
        List<Role> roles = roleMapper.selectRoleByUsername(username);

        for(Role role : roles){
            reuls.add(role.getName());
        }
        return reuls;
    }
}
