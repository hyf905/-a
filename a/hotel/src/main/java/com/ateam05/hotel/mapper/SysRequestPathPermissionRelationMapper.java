package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.SysRequestPathPermissionRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 路径权限关联表 Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysRequestPathPermissionRelationMapper extends BaseMapper<SysRequestPathPermissionRelation> {
    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    SysRequestPathPermissionRelation queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRequestPathPermissionRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 对象列表
     */
    List<SysRequestPathPermissionRelation> queryAll(SysRequestPathPermissionRelation sysRequestPathPermissionRelation);

    /**
     * 修改数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 影响行数
     */
    int update(SysRequestPathPermissionRelation sysRequestPathPermissionRelation);

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 影响行数
     */
    int deleteById(Integer id);
}
