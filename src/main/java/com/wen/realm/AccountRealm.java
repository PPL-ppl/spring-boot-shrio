package com.wen.realm;

import com.wen.entity.Account;
import com.wen.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /**
     * 授权
     *
     * @return
     * @parm principalCollection
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的用户信息
        Subject subject= SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        //设置角色
        Set<String> roles=new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        //设置权限
        info.addStringPermission (account.getPerms());
        return info;
    }

    /**
     * 认证
     *
     * @return
     * @parm authenticationToken
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //authenticationToken 客户输入的
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Account account = accountService.findByUsername(token.getUsername());
        if (account != null) {
            //account 查询到的用户信息  SimpleAuthenticationInfo 自动验证密码是否正确
            return new SimpleAuthenticationInfo(account, account.getPassword(), getName());
        }
        return null;
    }
}
