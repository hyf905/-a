package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.Hotel;
import com.ateam05.hotel.model.dto.HotelDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface HotelMapper extends BaseMapper<Hotel> {

    List<Hotel> find(HotelDTO hotelDTO);

}
