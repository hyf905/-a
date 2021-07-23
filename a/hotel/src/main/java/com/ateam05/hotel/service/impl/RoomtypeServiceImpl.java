package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.Roomtype;
import com.ateam05.hotel.mapper.RoomtypeMapper;
import com.ateam05.hotel.service.RoomtypeService;
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
public class RoomtypeServiceImpl extends ServiceImpl<RoomtypeMapper, Roomtype> implements RoomtypeService {

    @Autowired
    @Resource
    private RoomtypeMapper roomtypeMapper;

    @Override
    public List<Roomtype> find(Long hotelId,String bedtype, Float minPrice, Float maxPrice) {
        return roomtypeMapper.find(hotelId,bedtype,minPrice,maxPrice);
    }
}
