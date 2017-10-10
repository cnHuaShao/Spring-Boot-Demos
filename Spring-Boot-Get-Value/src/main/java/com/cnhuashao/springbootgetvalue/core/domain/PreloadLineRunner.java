package com.cnhuashao.springbootgetvalue.core.domain;

import com.cnhuashao.springbootgetvalue.core.bean.DemoBean;
import com.cnhuashao.springbootgetvalue.core.bean.MyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <b>TODO(com.cnhuashao.springbootgetvalue.core.domain @TODO)</b><br>
 * 版权所有 <a href="http://www.cnhuashao.com">中国-華少版本所有</a><br>
 * 这是一个依据Spring Boot官方API编写的相关功能测试用例，仅供学习使用。
 *
 * @Description: TODO 创建人：lz239
 * @CreateDate 2017 2017/10/10 11:49 10 修改人：lz239
 * <a href="mailto:lz2392504@gmail.com">cnhuashao $</a>
 * 修改备注：
 * @since V1.0
 */
@Component
public class PreloadLineRunner  implements CommandLineRunner {

    private static Logger log =LoggerFactory.getLogger(PreloadLineRunner.class);

    @Autowired
    private MyBean myBean;

    @Autowired
    private DemoBean demoBean;
    /**
     * 初始化将需要的参数加载至内存中
     * @param args
     */
    public void run(String... args){
        log.info("开始初始化加载数据");
        //Value方式进行获取配置参数。
        log.info(myBean.toString());
        //使用@ConfigurationProperties注解
        log.info(demoBean.toString());

        DemoBean t = new DemoBean();
        log.info(t.toString());
    }
}
