package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysRolePermissionRelation;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.model.dto.PageDTO;
import com.ateam05.hotel.service.SysPermissionService;
import com.ateam05.hotel.service.SysRolePermissionRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.SysRoleService;
import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.common.entity.JsonResponse;

import javax.annotation.Resource;
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
@RequestMapping("/api/sysRole")
public class SysRoleController {

    private final Logger logger = LoggerFactory.getLogger( SysRoleController.class );

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    SysPermissionService sysPermissionService;

    @Autowired
    SysRolePermissionRelationService  sysRolePermissionRelationService;

    //查看所有角色
    @GetMapping("/list")
    public JsonResponse listUser(PageDTO pageDTO){

        Page<SysRole> page = sysRoleService.list(pageDTO);
        return ResultTool.success(page);
    }

    //查看角色拥有的权限
    @GetMapping("/findPm/{id}")
    public JsonResponse listPm(@PathVariable("id") Integer id){
        List<SysPermission> permissions =sysRoleService.listPm(id);
        return ResultTool.success(permissions);
    }

    //给角色添加权限
    @PostMapping("/addPm/{id}")
    public  JsonResponse addPm(@PathVariable("id") Integer id,@RequestBody SysPermission permission){
        //根据权限查出SysPermissio
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_code",permission.getPermissionCode());
        SysPermission sysPermission = sysPermissionService.getOne(queryWrapper);
        //添加权限
        SysRolePermissionRelation relation = new SysRolePermissionRelation();
        relation.setPermissionId(sysPermission.getId());
        relation.setRoleId(id);
        boolean flag=sysRolePermissionRelationService.save(relation);
       if (flag==false){
           return  ResultTool.fail();
       }
       return ResultTool.success("添加权限成功");
    }
    //删除角色权限
    @DeleteMapping("/deletePm/{id}")
    public  JsonResponse deletePm(@PathVariable("id") Integer id,@RequestBody SysPermission permission){
        //根据权限查出SysPermissio
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_code",permission.getPermissionCode());
        SysPermission sysPermission = sysPermissionService.getOne(queryWrapper);
        //删除权限
        SysRolePermissionRelation relation =  sysRolePermissionRelationService.getOne(  new QueryWrapper<SysRolePermissionRelation>().eq("role_id",id).eq("permission_id",sysPermission.getId()));
        sysRolePermissionRelationService.removeById(relation);
        return ResultTool.success("删除权限成功");
    }


    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Integer id)throws Exception {
        SysRole  sysRole =  sysRoleService.select(id);
        return ResultTool.success(sysRole);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Integer id) throws Exception {
        sysRoleService.removeById(id);
        return ResultTool.success("删除成功");
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSysRole(@PathVariable("id") Integer  id,SysRole  sysRole) throws Exception {
        sysRole.setId(id);
        sysRoleService.updateById(sysRole);
        return ResultTool.success("更新成功");
    }


    /**
    * 描述:创建SysRole
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(SysRole  sysRole) throws Exception {
        sysRoleService.save(sysRole);
        return ResultTool.success("创建成功");
    }
}

