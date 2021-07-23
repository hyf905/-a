package com.ateam05.hotel.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Checkinfo对象", description="")
public class Checkinfo extends Model<Checkinfo> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "订单ID号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

            @ApiModelProperty(value = "房间类型")
    private Long hotelid;

    @ApiModelProperty(value = "房间类型")
    private Long roomtypeid;

            @ApiModelProperty(value = "订单时间")
    @TableField("checkInfoDate")
    private Date checkInfoDate;

            @ApiModelProperty(value = "入住时间")
    @TableField("checkInDate")
    private Date checkInDate;

            @ApiModelProperty(value = "退房时间")
    @TableField("checkOutDate")
    private Date checkOutDate;

            @ApiModelProperty(value = "订单状态")
    private Boolean status;

            @ApiModelProperty(value = "下单用户的id")
    @TableField("userId")
    private Long userId;
    @TableField("price")
    private Float price;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

