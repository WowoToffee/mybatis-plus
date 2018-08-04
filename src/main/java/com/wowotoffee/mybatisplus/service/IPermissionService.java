package com.wowotoffee.mybatisplus.service;

import com.wowotoffee.mybatisplus.entity.Permission;
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
public interface IPermissionService extends IService<Permission> {

    public Set<String> listPermissionNames(String userName);

    public Set<String> listPermissionURLs(String userName);

    public List<Permission> list() ;

    public boolean needInterceptor(String requestURI);
}
