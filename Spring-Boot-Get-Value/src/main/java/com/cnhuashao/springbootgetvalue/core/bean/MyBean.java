package com.cnhuashao.springbootgetvalue.core.bean;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 实体注入
 */
@Component
public class MyBean {

    @Value("${my.name}")
    private String name;
    @Value("${my.version}")
    private String version;
    @Value("${my.title}")
    private String title;

    @Value("${my.secret}")
    private String secret;

    public String toString(){
        return "myBean[name:"+name+";version:"+version+";title:"+title+";secret:"+secret;
    }
}
