package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.SysUserRoleRelation;
import com.ateam05.hotel.mapper.SysUserRoleRelationMapper;
import com.ateam05.hotel.service.SysUserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色关联关系表 服务实现类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Service
public class SysUserRoleRelationServiceImpl extends ServiceImpl<SysUserRoleRelationMapper, SysUserRoleRelation> implements SysUserRoleRelationService {
    @Resource
    SysUserRoleRelationMapper sysUserRoleRelationMapper;

    @Override
    public SysUserRoleRelation getByUid(Long id) {
        return sysUserRoleRelationMapper.getByUid(id);
    }
}
