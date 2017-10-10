package com.cnhuashao.springbootactivemqshiro.core.service;

import com.cnhuashao.springbootactivemqshiro.core.bean.UserBean;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <b>TODO(com.cnhuashao.springbootactivemqshiro.core.service @TODO)</b><br>
 * 版权所有 <a href="http://www.cnhuashao.com">中国-華少版本所有</a><br>
 * 这是一个依据Spring Boot官方API编写的相关功能测试用例，仅供学习使用。
 *
 * @Description: TODO 创建人：lz239
 * @CreateDate 2017 2017/10/10 14:51 10 修改人：lz239
 * <a href="mailto:lz2392504@gmail.com">cnhuashao $</a>
 * 修改备注：
 * @since V1.0
 */
@Service
public class LoginService {

    private static Logger log = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserBean userBean;

    public void loginSelect(UserBean ub){
        log.info("开始进行登录业务处理");


        //1、进行第一步基础用户密码效验
        //if (ub.getName().equals(userBean.getName()) && ub.getPwd().equals(userBean.getPwd())) {



        //开始进行用户效验。

        //2、开始打开SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:user.ini");
        //3、得到SecurityManager实例
        SecurityManager securityManager=factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(ub.getName(),ub.getPwd());

        try{
            subject.login(usernamePasswordToken);
        }catch (LockedAccountException e){
            log.error("该账号已被锁定");
        }catch (DisabledAccountException e){
            log.error("该账号已被禁用");
        }catch (UnknownAccountException e){
            log.error("账号不存在");
        }catch (ExcessiveAttemptsException e){
            log.error("登录失败次数过多");
        }catch (IncorrectCredentialsException e){
            log.error("用户名/密码错误");
        }catch (ExpiredCredentialsException e){
            log.error("登录凭证过期");
        }catch (AuthenticationException e){
            log.error("登录失败");
        }
        log.info(subject.isAuthenticated()? "登录成功":"登录失败");

        //退出登录
        subject.logout();

        log.info("登录业务处理完成");
    }

    public void loginSelectUserRealm(UserBean ub){
        log.info("开始进行登录业务处理");

        //1、开始打开SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:user-realm.ini");
        //2、得到SecurityManager实例
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(ub.getName(),ub.getPwd());
        try{
            subject.login(usernamePasswordToken);
        }catch (LockedAccountException e){
            log.error("该账号已被锁定");
        }catch (DisabledAccountException e){
            log.error("该账号已被禁用");
        }catch (UnknownAccountException e){
            log.error("账号不存在");
        }catch (ExcessiveAttemptsException e){
            log.error("登录失败次数过多");
        }catch (IncorrectCredentialsException e){
            log.error("用户名/密码错误");
        }catch (ExpiredCredentialsException e){
            log.error("登录凭证过期");
        }catch (AuthenticationException e){
            log.error("登录失败");
        }
        log.info(subject.isAuthenticated()? "登录成功":"登录失败");

        //退出登录
        subject.logout();

        log.info("登录业务处理完成");
    }
}
