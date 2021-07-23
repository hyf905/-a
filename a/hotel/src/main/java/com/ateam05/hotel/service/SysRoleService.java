package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.model.dto.PageDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysRoleService extends IService<SysRole> {

    Page<SysRole> list(PageDTO pageDTO);

    List<SysPermission> listPm(Integer id);

    SysRole select(Integer id);

    SysRole getByRoleName(String roleName);
}
