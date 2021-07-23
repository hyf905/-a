package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.Hoteltype;
import com.ateam05.hotel.model.dto.HoteltypeDTO;
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
public interface HoteltypeMapper extends BaseMapper<Hoteltype> {

    List<HoteltypeDTO> selectIsdeleted();
}
