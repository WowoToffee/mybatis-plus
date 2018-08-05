package com.wowotoffee.mybatisplus.web;

import com.wowotoffee.mybatisplus.entity.User;
import com.wowotoffee.mybatisplus.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 没有权限也可以访问的公共页面
 */
@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private IUserService iUserService;
    /**
     * 登录方法
     * @param model
     * @param name  用户名
     * @param password	用户密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String name, String password) {
        //所有Subject 都绑定到SecurityManager，与Subject的所有交互都会委托给SecurityManager；可以把Subject认为是一个门面；
        // SecurityManager才是实际的执行者。
        //SecurityUtils:是一个非常关键的类，这里可以获取到我们的全局的资源，和当前的线程相关的，放置在ThreadLocal里面的，
        // Subject也是如此哦，和当前的环境相关
        Subject subject = SecurityUtils.getSubject();
        //将用户的姓名和密码转化成ToKen
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            //登录，即身份验证(在执行login()这个方法时会先执行doGetAuthorizationInfo()这个方法进行认证 本系统写在realm.DatabaseRealm这个类中)
            subject.login(token);
            /**
             * 如果login方法执行完毕且没有抛出任何异常信息，那么便认为用户认证通过。之后在应用程序任意地方调用SecurityUtils.getSubject()
             * 都可以获取到当前认证通过的用户实例，使用subject.isAuthenticated()判断用户是否已验证都将返回true.   相反，如果login方法执行过程中抛出异常，
             * 那么将认为认证失败。Shiro有着丰富的层次鲜明的异常类来描述认证失败的原因
             */
            //获得当前的session
            Session session = subject.getSession();
            session.setAttribute("subject", subject);

            return "redirect:index";

        } catch (AuthenticationException e) {
            model.addAttribute("error", "验证失败");
            return "login";
        }
    }

    /**
     * 登录跳转
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String userLogin() {
        return "login";
    }

    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public String userIndex(){

        return "index";
    }

    /**
     * 权限不足界面
     * @return
     */
    @GetMapping("/unauthorized")
    public String userUnauthorized(){

        return "unauthorized";
    }

    /**
     * 登出功能，跳到登录界面
     * @return
     */
    @GetMapping("/doLogout")
    public String logout(){

        return "login";
    }

    /**
     * 用户注册
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(String name,String password){
        System.out.println(name+password);
        //加盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        User u = new User();
        u.setName(name);
        u.setPassword(encodedPassword);
        u.setSalt(salt);

        iUserService.insert(u);

        return "listUser";
    }
}
