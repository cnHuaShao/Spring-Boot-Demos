package com.cnhuashao.springbootactivemqshiro.core.realm;

import com.cnhuashao.springbootactivemqshiro.core.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>TODO(com.cnhuashao.springbootactivemqshiro.core.realm @TODO)</b><br>
 * 版权所有 <a href="http://www.cnhuashao.com">中国-華少版本所有</a><br>
 * 这是一个依据Spring Boot官方API编写的相关功能测试用例，仅供学习使用。
 *
 * @Description: TODO 创建人：lz239
 * @CreateDate 2017 2017/10/10 16:50 10 修改人：lz239
 * <a href="mailto:lz2392504@gmail.com">cnhuashao $</a>
 * 修改备注：
 * @since V1.0
 */
public class UserRealm implements Realm {
    private static Logger log = LoggerFactory.getLogger(UserRealm.class);
    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //判定token是否是UsernamePasswordToken的实例，返回boolean结果
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始进行自定义身份验证");
        String username=(String)token.getPrincipal();
        String password=new String((char[])token.getCredentials());
        //判断用户名是否错误
        if (!"cnhuashao".equals(username)){
            throw new UnknownAccountException();
        }
        //判断密码是否错误
        if (!"cnhuashao".equals(password)){
            throw new IncorrectCredentialsException();
        }
        log.info("自定义身份验证完成");
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
