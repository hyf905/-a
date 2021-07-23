package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.model.domain.SysUserRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户角色关联关系表 Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysUserRoleRelationMapper extends BaseMapper<SysUserRoleRelation> {
    /**
     * 通过userID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRoleRelation getByUid(Long id);
}
