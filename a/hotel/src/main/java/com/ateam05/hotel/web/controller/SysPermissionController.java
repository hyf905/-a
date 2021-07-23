package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.enums.ResultCode;
import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.model.dto.PageDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.SysPermissionService;
import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.common.entity.JsonResponse;

/**
 *
 *  前端控制器
 *
 *
 * @author hyf
 * @since 2021-06-17
 * @version v1.0
 */
@RestController
@RequestMapping("/api/sysPermission")
public class SysPermissionController {

    private final Logger logger = LoggerFactory.getLogger( SysPermissionController.class );

    @Autowired
    private SysPermissionService sysPermissionService;


    //查看所有的权限
    @GetMapping("/list")
    public JsonResponse listPm(PageDTO pageDTO){
        Page<SysPermission> page = sysPermissionService.list(pageDTO);
        return ResultTool.success(page);

    }

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResponse getById(@PathVariable("id") Integer id)throws Exception {
        SysPermission  sysPermission =  sysPermissionService.getById(id);
        return ResultTool.success(sysPermission);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResponse deleteById(@PathVariable("id") Integer id) throws Exception {
     boolean flag =   sysPermissionService.removeById(id);
      if(flag== false) return ResultTool.fail(ResultCode.NO_EXISTENT);
        return ResultTool.success("删除成功");
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSysPermission(@PathVariable("id") Integer  id,SysPermission  sysPermission) throws Exception {
        sysPermission.setId(id);
        sysPermissionService.updateById(sysPermission);
        return ResultTool.success("更新成功");
    }


    /**
    * 描述:创建SysPermission
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create( SysPermission  sysPermission) throws Exception {
        sysPermissionService.save(sysPermission);
        return ResultTool.success("创建成功");
    }
}

