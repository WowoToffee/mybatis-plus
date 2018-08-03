package com.wowotoffee.mybatisplus.service.impl;

import com.wowotoffee.mybatisplus.entity.User;
import com.wowotoffee.mybatisplus.mapper.UserMapper;
import com.wowotoffee.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
