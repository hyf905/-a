package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.Checkinfo;
import com.ateam05.hotel.model.dto.CheckinfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface CheckinfoService extends IService<Checkinfo> {
    /**
     * 查询多条数据
     *
     * @param userId 用户ID
     * @return 对象列表
     */
    List<CheckinfoDTO> querybyUserId(Long userId);

}
