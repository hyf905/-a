package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.Checkinfo;
import com.ateam05.hotel.mapper.CheckinfoMapper;
import com.ateam05.hotel.model.dto.CheckinfoDTO;
import com.ateam05.hotel.service.CheckinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Service
public class CheckinfoServiceImpl extends ServiceImpl<CheckinfoMapper, Checkinfo> implements CheckinfoService {
    @Resource
    private CheckinfoMapper checkinfoMapper;

    @Override
    public List<CheckinfoDTO> querybyUserId(Long userId){
        return this.checkinfoMapper.querybyuserId(userId);
    }

}
