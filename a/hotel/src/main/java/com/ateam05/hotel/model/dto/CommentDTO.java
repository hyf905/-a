package com.ateam05.hotel.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Long id;

    @ApiModelProperty(value = "酒店ID")
    @TableField("hotelId")
    private Long hotelId;

    @ApiModelProperty(value = "用户ID")
    @TableField("userId")
    private Long userId;

    @ApiModelProperty(value = "评论详情")
    private String details;

    private Long checkinfoid;

    private Float rate;

    private String photo;

    private Date date;

    private String username;
}
