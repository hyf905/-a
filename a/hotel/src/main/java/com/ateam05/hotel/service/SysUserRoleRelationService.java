package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.SysUserRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关联关系表 服务类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysUserRoleRelationService extends IService<SysUserRoleRelation> {

//根据userid查询
    SysUserRoleRelation getByUid(Long id);

}
