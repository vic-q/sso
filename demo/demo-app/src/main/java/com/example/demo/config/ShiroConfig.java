package com.example.demo.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import io.buji.pac4j.realm.Pac4jRealm;

/**
 * @author wangqing
 */
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/api/data", "user");
        filterMap.put("/api/callback", "anon");
        filterMap.put("/api/test", "anon");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        factoryBean.setLoginUrl("http://127.0.0.1:8101/login.html");
        return factoryBean;
    }

    private DefaultSecurityManager securityManager() {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(new Pac4jRealm());
        return securityManager;
    }
}
