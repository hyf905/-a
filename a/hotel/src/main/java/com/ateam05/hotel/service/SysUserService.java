package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.model.vo.SysUserVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);

    SysUser queryAll(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据账号查询用户
     *
     * @param userName
     * @return
     */
    SysUser selectByName(String userName);

    List<SysUserVo> lists();

    SysRole selectRoleById(Long id);
}
