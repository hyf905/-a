package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.Hotel;
import com.ateam05.hotel.mapper.HotelMapper;
import com.ateam05.hotel.model.dto.HotelDTO;
import com.ateam05.hotel.service.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Autowired
    @Resource
    private HotelMapper hotelMapper;

    @Override
    public List<Hotel> find(HotelDTO hotelDTO) {
        return hotelMapper.find(hotelDTO);
    }
}
