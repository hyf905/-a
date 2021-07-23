package com.ateam05.hotel.config.handler;

import com.alibaba.fastjson.JSON;
import com.ateam05.hotel.common.entity.JsonResponse;
import com.ateam05.hotel.common.enums.ResultCode;
import com.ateam05.hotel.common.utils.ResultTool;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Huyufan
 * @Description: 权限拒绝处理逻辑
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        JsonResponse result = ResultTool.fail(ResultCode.NO_PERMISSION);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
