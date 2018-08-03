package com.wowotoffee.mybatisplus.service;

import com.wowotoffee.mybatisplus.entity.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-03
 */
public interface IRoleService extends IService<Role> {
    public Set<String> selectNameByUsernameService(String username);
}
