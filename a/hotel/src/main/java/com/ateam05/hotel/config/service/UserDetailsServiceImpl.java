package com.ateam05.hotel.config.service;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.service.SysPermissionService;
import com.ateam05.hotel.service.SysRequestPathService;
import com.ateam05.hotel.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sun.net.www.protocol.http.AuthenticationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Huyufan
 * @Description:
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysRequestPathService sysRequestPathService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("账号不能为空");
        }
        //根据用户名查询用户
        SysUser sysUser = sysUserService.selectByName(username);
        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }
        if (sysUser.getEnabled()==false){
            throw new DisabledException("账号已经不可用");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (sysUser != null) {
            //获取该用户所拥有的权限
            List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                grantedAuthorities.add(grantedAuthority);
            });
        }
        return new User(sysUser.getAccount(), sysUser.getPassword(),  grantedAuthorities);
    }
}
