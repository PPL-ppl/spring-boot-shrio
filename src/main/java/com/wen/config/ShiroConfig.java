package com.wen.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.wen.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> map = new HashMap<>();
        map.put("/main", "authc");
        map.put("/index","authc");
        map.put("/manage", "perms[manage]");
        map.put("/administrator", "roles[administrator]");
        filterFactoryBean.setFilterChainDefinitionMap(map);
        //需要登录跳转
        filterFactoryBean.setLoginUrl("/login");
        //设置未授权页面
        filterFactoryBean.setUnauthorizedUrl("/unauth");
        return filterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("accountRealm") AccountRealm accountRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }


    @Bean
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
