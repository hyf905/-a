package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.enums.ResultCode;
import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.model.domain.*;
import com.ateam05.hotel.model.dto.PageDTO;
import com.ateam05.hotel.model.dto.RoleDto;
import com.ateam05.hotel.model.vo.SysUserVo;
import com.ateam05.hotel.service.SysPermissionService;
import com.ateam05.hotel.service.SysRoleService;
import com.ateam05.hotel.service.SysUserRoleRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.SysUserService;
import com.ateam05.hotel.common.entity.JsonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Permission;
import java.sql.Wrapper;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
@RequestMapping("/api/sysUser")
public class SysUserController {

    private final Logger logger = LoggerFactory.getLogger( SysUserController.class );

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    SysUserRoleRelationService sysUserRoleRelationService;
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysRoleService sysRoleService;


    //注册用户，默认为普通用户
    @PostMapping("/register")
    public JsonResponse registerUser( SysUser sysUser) {
        SysUser user = new SysUser();
        user.setAccount(sysUser.getAccount());
        if(sysUserService.queryAll( user )!=null){
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        user = new SysUser();
        user.setUserName(sysUser.getUserName());
        if(sysUserService.queryAll( user )!=null) {
            return ResultTool.fail(ResultCode.USER_USERNAME_ALREADY_EXIST);
        }
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUserService.save(sysUser);
        //注册默认为普通用户
        sysUserRoleRelationService.save(new SysUserRoleRelation().setUserId(sysUser.getId()).setRoleId(2));
        return ResultTool.success("注册成功");
    }

    //查看所有用户
    @GetMapping("/list")
    public JsonResponse listUser(){
            List<SysUserVo> list = sysUserService.lists();
            for (SysUserVo sysUser : list){
                sysUser.setRoleName(sysUserService.selectRoleById(sysUser.getId()).getRoleCode());
            }

            return ResultTool.success(list);

    }


    @RequestMapping("/register/error")
    public JsonResponse registerError(HttpServletRequest request){
        return ResultTool.fail(ResultCode.VERIFY_FALL);
    }


    //更改用户角色
    @PostMapping("/updateRole/{id}")
    public JsonResponse updateRole(@PathVariable("id") Long id, Integer roleId){
        SysUserRoleRelation sysUserRoleRelation = sysUserRoleRelationService.getByUid(id);
        //sysUserRoleRelation.setRoleId(Rid);
        UpdateWrapper<SysUserRoleRelation> updateWrapper = new UpdateWrapper<>();
       // updateWrapper.eq(SysUserRoleRelation::getId,);
        updateWrapper.eq("user_id",id).set("role_id",roleId);
        sysUserRoleRelationService.update(updateWrapper);
        return ResultTool.success("更改用户信息成功");
    }
//更改用户角色
//@PostMapping("/updateRole")
//public JsonResponse updateRole(@RequestBody RoleDto roleDto){
//    SysUserRoleRelation sysUserRoleRelation = sysUserRoleRelationService.getByUid(roleDto.getUserId());
//    //sysUserRoleRelation.setRoleId(Rid);
//    UpdateWrapper<SysUserRoleRelation> updateWrapper = new UpdateWrapper<>();
//    // updateWrapper.eq(SysUserRoleRelation::getId,);
//    updateWrapper.set("role_id",roleDto.getRoleId());
//    sysUserRoleRelationService.update(sysUserRoleRelation,updateWrapper);
//    return ResultTool.success();
//}

    //查看用户权限
    @GetMapping("/queryPm/{id}")
    public JsonResponse queryPerById(@PathVariable("id") Long id){
        List<SysPermission> permissions = sysPermissionService.selectListByUser(id);
        return ResultTool.success(permissions);
    }

    //查询用户的角色
    @GetMapping("/queryRole/{id}")
    public JsonResponse queryRoleById(@PathVariable("id") Long id){
        SysRole sysRole =sysUserService.selectRoleById(id);
        return ResultTool.success(sysRole);
    }



    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById( @PathVariable("id") Long id)throws Exception {
       SysUser sysUser =  sysUserService.getById(id);
        return ResultTool.success(sysUser);
    }

    //使用户不可用
    @PostMapping("/unable/{id}")
    public JsonResponse unable(@PathVariable("id") Long id) throws Exception{
        SysUser sysUser =sysUserService.getById(id);
        sysUserService.update(sysUser.setEnabled(false));
        return ResultTool.success("账号已经不可用");
    }

    //恢复账号可用
    @PostMapping("/able/{id}")
    public JsonResponse able(@PathVariable("id") Long id) throws Exception{
        SysUser sysUser =sysUserService.getById(id);
        sysUserService.update(sysUser.setEnabled(true));
        return ResultTool.success("账号已恢复");
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id")Long id) throws Exception {
        sysUserService.removeById(id);
        return ResultTool.success("删除成功");
    }
    /**
     * 描述：根据Id删除
     *
     */
    @DeleteMapping("/deletes")
    @ResponseBody
    public JsonResponse deleteByIds(@RequestParam Collection ids) throws Exception {
        sysUserService.removeByIds(ids);
        return ResultTool.success("删除成功");
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSysUser( SysUser sysUser) throws Exception {
        if (sysUser.getPassword()!=null)   sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        SysUser user = new SysUser();
        user.setAccount(sysUser.getAccount());
        if(sysUser.getAccount()!=null&&sysUserService.queryAll( user )!=null){
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        user = new SysUser();
        user.setUserName(sysUser.getUserName());
        if(sysUser.getUserName()!=null&&sysUserService.queryAll( user )!=null) {
            return ResultTool.fail(ResultCode.USER_USERNAME_ALREADY_EXIST);
        }
        sysUserService.update(sysUser);
        return ResultTool.success("更新成功");
    }

    //管理员更新用户
    @RequestMapping(value = "/Aupdate", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUser( SysUser sysUser,@RequestParam("roleName") String roleName) throws Exception {
        SysUserRoleRelation sysUserRoleRelation = sysUserRoleRelationService.getByUid(sysUser.getId());
        SysRole sysRole = sysRoleService.getByRoleName(roleName);
        //sysUserRoleRelation.setRoleId(Rid);
        UpdateWrapper<SysUserRoleRelation> updateWrapper = new UpdateWrapper<>();
        // updateWrapper.eq(SysUserRoleRelation::getId,);
        updateWrapper.eq("user_id",sysUser.getId()).set("role_id",sysRole.getId());
        sysUserRoleRelationService.update(updateWrapper);
        SysUser user = new SysUser();
        user.setAccount(sysUser.getAccount());
        if(sysUser.getAccount()!=null&&sysUserService.queryAll( user )!=null){
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        user = new SysUser();
        user.setUserName(sysUser.getUserName());
        if(sysUser.getUserName()!=null&&sysUserService.queryAll( user )!=null) {
            return ResultTool.fail(ResultCode.USER_USERNAME_ALREADY_EXIST);
        }
        sysUserService.update(sysUser);
        return ResultTool.success("更新成功");
    }

    /**
    * 描述:创建SysUser
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(SysUser  sysUser) throws Exception {
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUserService.save(sysUser);
        return ResultTool.success();
    }
}

