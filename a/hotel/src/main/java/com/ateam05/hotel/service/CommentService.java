package com.ateam05.hotel.service;

import com.ateam05.hotel.model.domain.Comment;
import com.ateam05.hotel.model.dto.CommentDTO;
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
public interface CommentService extends IService<Comment> {


    public float updateRate (Long hotelId);


    List<CommentDTO> getByHotelId(Long hotelId);
}
