package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.SysUserRoleRelationService;
import com.ateam05.hotel.model.domain.SysUserRoleRelation;
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
@RequestMapping("/api/sysUserRoleRelation")
public class SysUserRoleRelationController {

    private final Logger logger = LoggerFactory.getLogger( SysUserRoleRelationController.class );

    @Autowired
    private SysUserRoleRelationService sysUserRoleRelationService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Integer id)throws Exception {
        SysUserRoleRelation  sysUserRoleRelation =  sysUserRoleRelationService.getById(id);
        return ResultTool.success(sysUserRoleRelation);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Integer id) throws Exception {
        sysUserRoleRelationService.removeById(id);
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSysUserRoleRelation(@PathVariable("id") Integer  id,SysUserRoleRelation  sysUserRoleRelation) throws Exception {
        sysUserRoleRelation.setId(id);
        sysUserRoleRelationService.updateById(sysUserRoleRelation);
        return ResultTool.success(null);
    }


    /**
    * 描述:创建SysUserRoleRelation
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(SysUserRoleRelation  sysUserRoleRelation) throws Exception {
        sysUserRoleRelationService.save(sysUserRoleRelation);
        return ResultTool.success(null);
    }
}

