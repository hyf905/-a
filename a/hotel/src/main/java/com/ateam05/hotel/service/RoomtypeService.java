package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.Roomtype;
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
public interface RoomtypeService extends IService<Roomtype> {

    //搜索房型
    List<Roomtype> find(Long hotelId,String bedtype, Float minPrice, Float maxPrice);

}
