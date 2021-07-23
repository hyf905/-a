package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.dto.PageDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermission queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysPermission> queryAllByLimit(int offset, int limit);


    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    SysPermission update(SysPermission sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Long userId);
    /**
     * 查看角色的权限列表
     *
     * @param roleId
     * @return
     */
    //List<SysPermission>selectListByRole(Integer roleId);


    /**
     * 查询具体某个接口的权限
     *
     * @param path
     * @return
     */
    List<SysPermission> selectListByPath(String path);

    Page<SysPermission> list(PageDTO pageDTO);
}
