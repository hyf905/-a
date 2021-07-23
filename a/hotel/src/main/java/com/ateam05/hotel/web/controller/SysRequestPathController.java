package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysRequestPathPermissionRelation;
import com.ateam05.hotel.model.dto.PageDTO;
import com.ateam05.hotel.service.SysPermissionService;
import com.ateam05.hotel.service.SysRequestPathPermissionRelationService;
import com.ateam05.hotel.service.SysRolePermissionRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.SysRequestPathService;
import com.ateam05.hotel.model.domain.SysRequestPath;
import com.ateam05.hotel.common.entity.JsonResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
@RequestMapping("/api/sysRequestPath")
public class SysRequestPathController {

    private final Logger logger = LoggerFactory.getLogger( SysRequestPathController.class );

    @Autowired
    private SysRequestPathService sysRequestPathService;
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysRequestPathPermissionRelationService sysRequestPathPermissionRelationService;

    //查看所有请求路径
    @GetMapping("/list")
    public JsonResponse listPath(PageDTO pageDTO){

        Page<SysRequestPath> page = sysRequestPathService.list(pageDTO);
        return ResultTool.success(page);

    }

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Integer id)throws Exception {
        SysRequestPath  sysRequestPath =  sysRequestPathService.getById(id);
        return ResultTool.success(sysRequestPath);
    }

    //根据PermissionCode查找
    @GetMapping("/find")
    public JsonResponse selectByPid(@RequestBody SysPermission permission){
        List<SysRequestPath>  sysRequestPaths = sysRequestPathService.selectByPermission(permission.getPermissionCode());
        return ResultTool.success(sysRequestPaths);
    }

    //根据Url查询
    @GetMapping("/select")
    public JsonResponse selectByUrl( String url){
        SysRequestPath sysRequestPath = sysRequestPathService.selectByUrl(url);
        return ResultTool.success(sysRequestPath);
    }

    //根据Url查询需要的权限
    @GetMapping("/selectPm")
    public JsonResponse selectPmByUrl( String url){
        List<SysPermission> sysPermissions =sysPermissionService.selectListByPath(url);
        if (sysPermissions.size()==0) return ResultTool.fail();
        SysPermission sysPermission =sysPermissions.get(0);
        String permission = sysPermission.getPermissionCode();
        return ResultTool.success(permission);
    }
    //给Url添加需要的权限
    @PostMapping("/addPm")
    public JsonResponse addPermissionByUrl(@RequestBody SysRequestPath sysRequestPath,String permission){
        //先插入RequestPath表中
        sysRequestPathService.save(sysRequestPath);
        //根据要添加的权限查出SysPermissio
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_code",permission);
        SysPermission sysPermission = sysPermissionService.getOne(queryWrapper);
        SysRequestPath requestPath=sysRequestPathService.selectByUrl(sysRequestPath.getUrl());
        //更新关联表
        SysRequestPathPermissionRelation relation = new SysRequestPathPermissionRelation();
        relation.setPermissionId(sysPermission.getId());
        relation.setUrlId(requestPath.getId());
        boolean flag=sysRequestPathPermissionRelationService.save(relation);
        //判断是否插入成功
        if (flag==false) return  ResultTool.fail();
        return ResultTool.success();

    }
    /**
     * 描述：根据Id 更新权限
     *
     */
    @RequestMapping(value = "/updatePm/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updatePathPm(@PathVariable("id") Integer id,@RequestBody SysPermission permission)  throws Exception {
        //根据权限查出SysPermissio
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_code",permission.getPermissionCode());
        SysPermission sysPermission = sysPermissionService.getOne(queryWrapper);
        //更新权限
        SysRequestPathPermissionRelation relation =  sysRequestPathPermissionRelationService.getOne(  new QueryWrapper<SysRequestPathPermissionRelation>().eq("url_id",id));
        relation.setPermissionId(sysPermission.getId());
        sysRequestPathPermissionRelationService.update(relation);
        return ResultTool.success(null);
    }
    /**
     * 描述：根据Id删除权限
     *
     */
    @RequestMapping(value = "/deletePm/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse deletePathPm(@PathVariable("id") Integer id)  throws Exception {

        //删除权限
        SysRequestPathPermissionRelation relation =  sysRequestPathPermissionRelationService.getOne(  new QueryWrapper<SysRequestPathPermissionRelation>().eq("url_id",id));
        sysRequestPathPermissionRelationService.removeById(relation);
        return ResultTool.success(null);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/deleteOne", method = RequestMethod.DELETE)
    public JsonResponse deleteOne(@RequestBody SysRequestPath sysRequestPath) throws Exception {
        sysRequestPathService.deleteById(sysRequestPath.getId());
        QueryWrapper<SysRequestPathPermissionRelation> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("url_id",sysRequestPath.getId());
        SysRequestPathPermissionRelation relation =sysRequestPathPermissionRelationService.getOne(queryWrapper);
        sysRequestPathPermissionRelationService.deleteById(relation.getId());
        return ResultTool.success(null);
    }
    /**
     * 描述：根据Id批量删除
     *
     */
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deletes(@RequestParam Collection ids) throws Exception {
        sysRequestPathService.removeByIds(ids);
        //从关联表中删除
        Iterator<Integer> iterator = (Iterator<Integer>) ids.iterator();
        while(iterator.hasNext()){
            QueryWrapper<SysRequestPathPermissionRelation> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("url_id",iterator.next());
            SysRequestPathPermissionRelation relation =sysRequestPathPermissionRelationService.getOne(queryWrapper);
            sysRequestPathPermissionRelationService.deleteById(relation.getId());
        }
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSysRequestPath(@RequestBody SysRequestPath sysRequestPath)  throws Exception {
        sysRequestPathService.update(sysRequestPath);
        return ResultTool.success(null);
    }


    /**
    * 描述:创建SysRequestPath
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(SysRequestPath  sysRequestPath) throws Exception {
        sysRequestPathService.save(sysRequestPath);
        return ResultTool.success(null);
    }
}

