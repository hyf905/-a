package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.mapper.SysRoleMapper;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.model.dto.PageDTO;
import com.ateam05.hotel.service.SysRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    SysRoleMapper sysRoleMapper;
    @Override
    public Page<SysRole> list(PageDTO pageDTO) {
        return sysRoleMapper.list(new Page<SysRole>(pageDTO.getPageNo(),pageDTO.getPageSize()));
    }

    @Override
    public List<SysPermission> listPm(Integer id) {
        return sysRoleMapper.listPm(id);
    }

    @Override
    public SysRole select(Integer id) {
        return sysRoleMapper.select(id);
    }

    @Override
    public SysRole getByRoleName(String roleName) {
        return sysRoleMapper.getByRoleName(roleName);
    }
}
