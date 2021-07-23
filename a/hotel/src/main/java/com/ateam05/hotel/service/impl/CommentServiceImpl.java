package com.ateam05.hotel.service.impl;

import com.ateam05.hotel.model.domain.Comment;
import com.ateam05.hotel.mapper.CommentMapper;
import com.ateam05.hotel.model.dto.CommentDTO;
import com.ateam05.hotel.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    /*
        更新酒店评分
     */
    @Autowired
    @Resource
    private CommentMapper commentMapper;

    @Override
    public float updateRate(Long hotelId){return commentMapper.count(hotelId);}

    @Override
    public List<CommentDTO> getByHotelId(Long hotelId) {
        return commentMapper.getByHotelId(hotelId);
    }

    ;


}
