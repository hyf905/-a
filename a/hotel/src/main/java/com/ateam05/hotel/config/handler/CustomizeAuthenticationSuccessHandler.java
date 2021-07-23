package com.ateam05.hotel.config.handler;

import com.alibaba.fastjson.JSON;
import com.ateam05.hotel.common.entity.JsonResponse;
import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.model.vo.UserVo;
import com.ateam05.hotel.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Huyufan
 * @Description: 登录成功处理逻辑
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    SysUserService sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间
        User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.selectByName(userDetails.getUsername());
        Date date = new Date();
        long longTime = date.getTime();
        Timestamp timestamp = new Timestamp(longTime);
        sysUser.setLastLoginTime(timestamp);
        SysRole sysRole =sysUserService.selectRoleById(sysUser.getId());
        sysUserService.update(sysUser);

        //返回json数据
        UserVo userVo = new UserVo();
        userVo.setUserName(sysUser.getUserName());
        userVo.setRoleCode(sysRole.getRoleCode());
        userVo.setId(sysUser.getId());
        JsonResponse result = ResultTool.success(userVo);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
