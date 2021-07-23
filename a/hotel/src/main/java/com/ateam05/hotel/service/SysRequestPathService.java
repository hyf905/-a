package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.model.domain.SysRequestPath;
import com.ateam05.hotel.model.dto.PageDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 请求路径 服务类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface SysRequestPathService extends IService<SysRequestPath> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRequestPath queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRequestPath> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysRequestPath 实例对象
     * @return 实例对象
     */
    SysRequestPath insert(SysRequestPath sysRequestPath);

    /**
     * 修改数据
     *
     * @param sysRequestPath 实例对象
     * @return 实例对象
     */
    SysRequestPath update(SysRequestPath sysRequestPath);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    //根据permission查询
    List<SysRequestPath> selectByPermission(String permission);
    //根据url查询
    SysRequestPath selectByUrl(String url);
    //根据url查询SysPermission
    SysPermission selectPmByUrl();

    Page<SysRequestPath> list(PageDTO pageDTO);
}
