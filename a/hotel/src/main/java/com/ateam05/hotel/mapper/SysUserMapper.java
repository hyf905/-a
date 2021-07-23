package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.model.dto.PageDTO;
import com.ateam05.hotel.model.vo.SysUserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    SysUser queryAll(SysUser sysUser);



    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
  //  int deleteById(Long id);

    /**
     * 根据账号查询用户
     *
     * @param userName
     * @return
     */
    SysUser selectByName(String userName);

    List<SysUserVo> list();

    SysRole selectRoleById(Long id);
}
