package com.wowotoffee.mybatisplus.mapper;

import com.wowotoffee.mybatisplus.entity.Permission;
import com.wowotoffee.mybatisplus.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-03
 */
@Mapper
@Resource
public interface RoleMapper extends BaseMapper<Role> {
    public List<Permission> selectPermissionByUsername(String username);
    public List<Role> selectRoleByUsername(String username);
}
