package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.model.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    Page<SysRole> list(Page<SysRole> pageDTO);

    List<SysPermission> listPm(Integer id);

    SysRole select(Integer id);

    SysRole getByRoleName(String roleName);
}
