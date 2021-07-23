package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.Roomtype;
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
public interface RoomtypeMapper extends BaseMapper<Roomtype> {

    List<Roomtype> find(Long hotelId,String bedtype, Float minPrice, Float maxPrice);

}
