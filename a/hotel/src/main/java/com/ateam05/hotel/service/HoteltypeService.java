package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.Hoteltype;
import com.ateam05.hotel.model.dto.HoteltypeDTO;
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
public interface HoteltypeService extends IService<Hoteltype> {

    List<HoteltypeDTO> selectIsdeleted();
}
