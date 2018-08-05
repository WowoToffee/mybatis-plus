package com.wowotoffee.mybatisplus.config;

import com.wowotoffee.mybatisplus.service.IPermissionService;
import com.wowotoffee.mybatisplus.util.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    private IPermissionService iPermissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //判断iPermissionService 是否被注入进去
        if(null==iPermissionService)
            iPermissionService = SpringContextUtils.getContext().getBean(IPermissionService.class);
        String requestURI = getPathWithinApplication(request);
        System.out.println("requestURI:" + requestURI);

        //获取subject
        Subject subject = SecurityUtils.getSubject();

        /**
         * subject.isAuthenticated()表示用户进行了身份验证登录的，即使有Subject.login进行了登录；
         * subject.isRemembered()：表示用户是通过记住我登录的，此时可能并不是真正的你（如你的朋友使用你的电脑，或者你的cookie被窃取）在访问的；
         * 且两者二选一，即subject.isAuthenticated()==true，则subject.isRemembered()==false；反之一样。
         */
        // 如果没有登录，就跳转到登录页面
        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/login");
            return false;
        }

        // 看看这个路径权限里有没有该权限的维护，如果没有维护，一律放行(也可以改为一律不放行)
        //这里查询permission 表中的内容
        boolean needInterceptor = iPermissionService.needInterceptor(requestURI);
        if (!needInterceptor) {
            return true;
        } else {
            boolean hasPermission = false;
            String userName = subject.getPrincipal().toString();
            Set<String> permissionUrls = iPermissionService.listPermissionURLs(userName);
            for (String url : permissionUrls) {
                // 这就表示当前用户有这个权限
                if (url.equals(requestURI)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission)
                return true;
            else {
                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");

                subject.getSession().setAttribute("ex", ex);

                WebUtils.issueRedirect(request, response, "/unauthorized");
                return false;
            }

        }
    }
}
