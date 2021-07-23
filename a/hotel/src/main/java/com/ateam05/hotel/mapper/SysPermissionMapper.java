package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermission queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysPermission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermission 实例对象
     * @return 对象列表
     */
    List<SysPermission> queryAll(SysPermission sysPermission);



    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 影响行数
     */
    int update(SysPermission sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询用户的权限
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Long userId);

    /**
     * 查询具体某个接口的权限
     *
     * @param path 接口路径
     * @return
     */
    List<SysPermission> selectListByPath(String path);

    Page<SysPermission> list(Page<SysPermission> sysPermissionPage);
}
