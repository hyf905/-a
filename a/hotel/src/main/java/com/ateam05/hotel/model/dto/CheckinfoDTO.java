package com.ateam05.hotel.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CheckinfoDTO {
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

    private Float price;

    private String hotelNameCn;
}
