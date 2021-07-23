package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.SysRole;
import com.ateam05.hotel.model.domain.SysUser;
import com.ateam05.hotel.mapper.SysUserMapper;
import com.ateam05.hotel.model.vo.SysUserVo;
import com.ateam05.hotel.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Long id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserDao.deleteById(id) > 0;
    }

    @Override
    public SysUser selectByName(String userName) {
        return this.sysUserDao.selectByName(userName);
    }

    @Override
    public List<SysUserVo> lists() {
        return sysUserDao.list();
    }

    @Override
    public SysRole selectRoleById(Long id) {
        return sysUserDao.selectRoleById(id);
    }

    @Override
    public SysUser queryAll(SysUser sysUser) {
        return sysUserDao.queryAll(sysUser);
    }
}
