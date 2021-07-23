package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.SysRequestPathPermissionRelationService;
import com.ateam05.hotel.model.domain.SysRequestPathPermissionRelation;
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
@RequestMapping("/api/sysRequestPathPermissionRelation")
public class SysRequestPathPermissionRelationController {

    private final Logger logger = LoggerFactory.getLogger( SysRequestPathPermissionRelationController.class );

    @Autowired
    private SysRequestPathPermissionRelationService sysRequestPathPermissionRelationService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Integer id)throws Exception {
        SysRequestPathPermissionRelation  sysRequestPathPermissionRelation =  sysRequestPathPermissionRelationService.getById(id);
        return ResultTool.success(sysRequestPathPermissionRelation);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Integer id) throws Exception {
        sysRequestPathPermissionRelationService.removeById(id);
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSysRequestPathPermissionRelation(@PathVariable("id") Integer  id,SysRequestPathPermissionRelation  sysRequestPathPermissionRelation) throws Exception {
        sysRequestPathPermissionRelation.setId(id);
        sysRequestPathPermissionRelationService.updateById(sysRequestPathPermissionRelation);
        return ResultTool.success(null);
    }


    /**
    * 描述:创建SysRequestPathPermissionRelation
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(SysRequestPathPermissionRelation  sysRequestPathPermissionRelation) throws Exception {
        sysRequestPathPermissionRelationService.save(sysRequestPathPermissionRelation);
        return ResultTool.success(null);
    }
}

