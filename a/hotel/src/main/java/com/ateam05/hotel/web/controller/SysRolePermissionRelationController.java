package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.SysRolePermissionRelationService;
import com.ateam05.hotel.model.domain.SysRolePermissionRelation;
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
@Controller
@RequestMapping("/api/sysRolePermissionRelation")
public class SysRolePermissionRelationController {

    private final Logger logger = LoggerFactory.getLogger( SysRolePermissionRelationController.class );

    @Autowired
    private SysRolePermissionRelationService sysRolePermissionRelationService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Integer id)throws Exception {
        SysRolePermissionRelation  sysRolePermissionRelation =  sysRolePermissionRelationService.getById(id);
        return ResultTool.success(sysRolePermissionRelation);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Integer id) throws Exception {
        sysRolePermissionRelationService.removeById(id);
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSysRolePermissionRelation(@PathVariable("id") Integer  id,SysRolePermissionRelation  sysRolePermissionRelation) throws Exception {
        sysRolePermissionRelation.setId(id);
        sysRolePermissionRelationService.updateById(sysRolePermissionRelation);
        return ResultTool.success(null);
    }


    /**
    * 描述:创建SysRolePermissionRelation
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(SysRolePermissionRelation  sysRolePermissionRelation) throws Exception {
        sysRolePermissionRelationService.save(sysRolePermissionRelation);
        return ResultTool.success(null);
    }
}

