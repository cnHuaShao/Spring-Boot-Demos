package com.cnhuashao.springbootgetvalue.core.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <b>TODO(com.cnhuashao.springbootgetvalue.core.bean @TODO)</b><br>
 * 版权所有 <a href="http://www.cnhuashao.com">中国-華少版本所有</a><br>
 * 这是一个依据Spring Boot官方API编写的相关功能测试用例，仅供学习使用。
 *
 * @Description: TODO 创建人：cnHuashao
 * @CreateDate 2017 2017/10/10 11:38 10 修改人：cnHuashao
 * <a href="mailto:lz2392504@gmail.com"> cnHuashao</a>
 * 修改备注：
 * @since V1.0
 */
@Component
@ConfigurationProperties(prefix = "person")
public class DemoBean {

    private boolean enabled;

    private int ttt;

    private String name;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTtt() {
        return ttt;
    }

    public void setTtt(int ttt) {
        this.ttt = ttt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "DemoBean[enabled:"+enabled+";ttt:"+ttt+";name:"+name;
    }
}
