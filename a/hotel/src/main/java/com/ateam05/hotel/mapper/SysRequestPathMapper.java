package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysRequestPath;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 请求路径 Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysRequestPathMapper extends BaseMapper<SysRequestPath> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRequestPath queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRequestPath> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRequestPath 实例对象
     * @return 对象列表
     */
    List<SysRequestPath> queryAll(SysRequestPath sysRequestPath);

    /**
     * 新增数据
     *
     * @param sysRequestPath 实例对象
     * @return 影响行数
     */

    int update(SysRequestPath sysRequestPath);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<SysRequestPath>  selectById(Integer pid);

    SysRequestPath selectByUrl(String url);

    //g根据Url查询SysPermission
    SysPermission selectPmByUrl();

    //根据permissionCode查询
    List<SysRequestPath> selectByPermission(String permission);

    Page<SysRequestPath> list(Page<SysRequestPath> sysRequestPathPage);
}
