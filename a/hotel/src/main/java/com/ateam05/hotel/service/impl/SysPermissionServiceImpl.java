package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.SysPermission;
import com.ateam05.hotel.mapper.SysPermissionMapper;
import com.ateam05.hotel.model.dto.PageDTO;
import com.ateam05.hotel.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysPermission queryById(Integer id) {
        return this.sysPermissionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysPermission> queryAllByLimit(int offset, int limit) {
        return this.sysPermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public SysPermission update(SysPermission sysPermission) {
        this.sysPermissionDao.update(sysPermission);
        return this.queryById(sysPermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysPermissionDao.deleteById(id) > 0;
    }

    @Override
    public List<SysPermission> selectListByUser(Long userId) {
        return sysPermissionDao.selectListByUser(userId);
    }

    @Override
    public List<SysPermission> selectListByPath(String path) {
        return sysPermissionDao.selectListByPath(path);
    }

    @Override
    public Page<SysPermission> list(PageDTO pageDTO) {
        return sysPermissionDao.list(new Page<SysPermission>(pageDTO.getPageNo(),pageDTO.getPageSize()));
    }
}
