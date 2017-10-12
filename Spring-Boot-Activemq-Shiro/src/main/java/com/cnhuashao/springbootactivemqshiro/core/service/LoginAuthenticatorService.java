package com.cnhuashao.springbootactivemqshiro.core.service;

import com.cnhuashao.springbootactivemqshiro.core.bean.UserBean;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <b>TODO(com.cnhuashao.springbootactivemqshiro.core.service @TODO)</b><br>
 * 版权所有 <a href="http://www.cnhuashao.com">中国-華少版本所有</a><br>
 * 这是一个依据Spring Boot官方API编写的相关功能测试用例，仅供学习使用。
 *
 * @Description: TODO 创建人：lz239
 * @CreateDate 2017 2017/10/12 10:18 10 修改人：lz239
 * <a href="mailto:lz2392504@gmail.com">cnhuashao $</a>
 * 修改备注：
 * @since V1.0
 */
@Service
public class LoginAuthenticatorService {
    private static Logger log = LoggerFactory.getLogger(LoginService.class);

    /**
     * 基础登录效验
     * @param configFile
     * @param userBean
     */
    private void login(String configFile, UserBean userBean){
        //根据传入的ini配置文件路径进行初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager= factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //生成要身份认证的subject和token
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userBean.getName(),userBean.getPwd());

        //开始进行登录效验
        subject.login(token);

    }

    /**
     * 测试用例：所有效验成功，则登录成功
     */
    @Test
    public void AllSuccessfulStrategyWithSuccess(){
        UserBean userBean =new UserBean();
        userBean.setName("cnhuashao");
        userBean.setPwd("cnhuashao");
        login("classpath:user-authenticatior-all-success.ini",userBean);
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其中包含了Realm验证成功的身份信息。
        PrincipalCollection principalCollection = subject.getPrincipals();

        //Assert.assertEquals(1,principalCollection.asList().size());
        Assert.assertEquals(2,principalCollection.asList());

        /*log.info(principalCollection.asList().size()+"");
        Object user = principalCollection.asList().get(1);
        log.info(user.toString());*/
    }

    /**
     * 测试用例：其中一个realm执行失败后的结果,第二个Realm报错，因起用户名不匹配导致验证错误，
     */
    @Test
    public void AllSuccessfulStrategyWithFail(){
        UserBean userBean =new UserBean();
        userBean.setName("cnhuashao");
        userBean.setPwd("cnhuashao");
        login("classpath:user-authenticatior-all-fail.ini",userBean);
    }
}
