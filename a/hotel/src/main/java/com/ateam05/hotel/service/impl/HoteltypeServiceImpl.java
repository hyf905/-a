package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.mapper.HotelMapper;
import com.ateam05.hotel.model.domain.Hoteltype;
import com.ateam05.hotel.mapper.HoteltypeMapper;
import com.ateam05.hotel.model.dto.HoteltypeDTO;
import com.ateam05.hotel.service.HoteltypeService;
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
public class HoteltypeServiceImpl extends ServiceImpl<HoteltypeMapper, Hoteltype> implements HoteltypeService {

    @Resource
    private HoteltypeMapper hoteltypeMapper;

    @Override
    public List<HoteltypeDTO> selectIsdeleted() {
        return hoteltypeMapper.selectIsdeleted();
    }
}
