package com.ateam05.hotel.config.handler;

import com.ateam05.hotel.config.service.MyGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Author: Huyufan
 * @Description: 访问决策管理器
 */
@Component
public class CustomizeAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.getAttribute();
            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足!");
    }
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//
//        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
//        String url, method;
//        AntPathRequestMatcher matcher;
//        for (GrantedAuthority ga : authentication.getAuthorities()) {
//            if (ga instanceof MyGrantedAuthority) {
//                MyGrantedAuthority urlGrantedAuthority = (MyGrantedAuthority) ga;
//                url = urlGrantedAuthority.getPermissionUrl();
//                method = urlGrantedAuthority.getMethod();
//                matcher = new AntPathRequestMatcher(url);
//                if (matcher.matches(request)) {
//                    //当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
//                    if (method.equals(request.getMethod()) || "ALL".equals(method)) {
//                        return;
//                    }
//                }
//            }
//        }
//        throw new AccessDeniedException("权限不足！");
//    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
