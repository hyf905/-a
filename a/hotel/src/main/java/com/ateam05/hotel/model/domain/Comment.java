package com.ateam05.hotel.model.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Comment对象", description="")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
