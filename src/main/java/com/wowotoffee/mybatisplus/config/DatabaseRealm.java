package com.wowotoffee.mybatisplus.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wowotoffee.mybatisplus.entity.User;
import com.wowotoffee.mybatisplus.service.IPermissionService;
import com.wowotoffee.mybatisplus.service.IRoleService;
import com.wowotoffee.mybatisplus.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 能进入到这里，表示账号已经通过验证了
        String userName = (String) principalCollection.getPrimaryPrincipal();
        // 通过service获取角色和权限
        Set<String> permissions = permissionService.listPermissionNames(userName);
        System.out.println(permissions+"permissions");
        Set<String> roles = roleService.selectNameByUsernameService(userName);
        System.out.println(roles+"roles");
        // 授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        // 把通过service获取到的角色和权限放进去
        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;
    }

    /**
     * 验证密码
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userName = token.getPrincipal().toString();

        // 获取数据库中的密码
        User user = iUserService.selectOne(new EntityWrapper<User>()
                .eq("name", userName));
        //密码
        String passwordInDB = user.getPassword();
        //密码盐
        String salt = user.getSalt();

        // 认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        // 盐也放进去
        // 这样通过applicationContext-shiro.xml里配置的 HashedCredentialsMatcher 进行自动校验
        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt),
                getName());
        return a;
    }
}
