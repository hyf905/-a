package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.Comment;
import com.ateam05.hotel.model.dto.CommentDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /*
     *更新酒店评分
     * @param hotelId 酒店ID
     * @return 浮点数
     */
    Float count(@Param("hotelId")long hotelId);

    List<CommentDTO> getByHotelId(Long hotelId);
}
