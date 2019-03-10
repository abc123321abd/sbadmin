package cn.edu.ctbu.sbadmin.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by tms on 18/5/5.
 */
@Component("MyAppConfig")
@ConfigurationProperties
@PropertySource("classpath:/myapp.properties")
@Data
public class MyAppConfig {
    //app的名称
    private String appName;

    //默认头像url
    private  String defaultHeaderURL;

    //app的版本
    private  String appVersion;


    //app的简称
    private String appShortName;

    //app版权
    private  String copyright;


}
