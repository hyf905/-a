package com.ateam05.hotel.config.handler;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.service.SysPermissionService;
import com.ateam05.hotel.service.SysRequestPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Huyufan
 * @Description:
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysRequestPathService sysRequestPathService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        System.out.println(requestUrl);
        if(Pattern.matches(".*[\\?]*",requestUrl)){
            String[] requestUrls =requestUrl.split("\\?");
            requestUrl =requestUrls[0];
        }
        //正则匹配数字
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(requestUrl);
        if(m.matches()){
            String regex = "[0-9]+";
            requestUrl=requestUrl.replaceAll(regex,"");
            requestUrl=requestUrl.substring(0,requestUrl.length()-1);
        }
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        System.out.println(requestUrl);
        //查询具体某个接口的权限
        List<SysPermission> permissionList =  sysPermissionService.selectListByPath(requestUrl);
    //    String method =sysRequestPathService.selectByUrl(requestUrl).getMethod();
      //  String method2 =request.getMethod();
        if(permissionList == null || permissionList.size() == 0 ){
            //请求路径没有配置权限，表明该请求接口可以任意访问
            return null;
        }
        String[] attributes = new String[permissionList.size()];
        for(int i = 0;i<permissionList.size();i++){
            attributes[i] = permissionList.get(i).getPermissionCode();
        }
        return SecurityConfig.createList(attributes);
    }

//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        Collection<ConfigAttribute> co=new ArrayList<>();
//        co.add(new SecurityConfig("null"));
//        return co;
//    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
