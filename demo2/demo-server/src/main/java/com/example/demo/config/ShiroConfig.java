package com.example.demo.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
        filterMap.put("/api/auth", "anon");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }

    private DefaultSecurityManager securityManager() {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(new UserRealm());
        return securityManager;
    }
}
