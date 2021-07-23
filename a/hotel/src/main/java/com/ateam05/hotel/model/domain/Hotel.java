package com.ateam05.hotel.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
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
@ApiModel(value="Hotel对象", description="")
public class Hotel extends Model<Hotel> {

    private static final long serialVersionUID = 1L;


            @ApiModelProperty(value = "酒店id")
    private Long id;

            @ApiModelProperty(value = "品牌id")
    private String brandId;

            @ApiModelProperty(value = "品牌名")
    private String brandName;

            @ApiModelProperty(value = "酒店英文名")
    private String hotelNameEn;

            @ApiModelProperty(value = "酒店中文名")
    private String hotelNameCn;

            @ApiModelProperty(value = "地址")
    private String address;

            @ApiModelProperty(value = "城市")
    private String city;

            @ApiModelProperty(value = "星级")
    private Float starRating;

            @ApiModelProperty(value = "经度")
    private Float longitude;

            @ApiModelProperty(value = "纬度")
    private Float latitude;

            @ApiModelProperty(value = "入房时间")
    private String checkin;

            @ApiModelProperty(value = "退房时间")
    private String checkout;

            @ApiModelProperty(value = "房间数")
    private String numberrooms;

            @ApiModelProperty(value = "描述")
    private String overview;

            @ApiModelProperty(value = "照片")
    private String photo;

            @ApiModelProperty(value = "开业年份")
    private String yearopened;

            @ApiModelProperty(value = "装修年份")
    private String yearrenovated;

    private Long hoteltypeid;

    private Long cityId;

    private Float price;

    private Float avgrate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
