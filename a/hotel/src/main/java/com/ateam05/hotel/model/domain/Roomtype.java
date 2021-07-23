package com.ateam05.hotel.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Roomtype对象", description="")
public class Roomtype extends Model<Roomtype> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roomtypeName;

            @ApiModelProperty(value = "床型")
    private String bedtype;

            @ApiModelProperty(value = "有无早餐")
    private Boolean breakfast;

            @ApiModelProperty(value = "面积")
    private String area;

            @ApiModelProperty(value = "最大入住人数")
    private String max;

            @ApiModelProperty(value = "价格")
    private Float price;

    private String photo;

            @ApiModelProperty(value = "设施服务")
    private String service;

    private Integer amount;

    @TableField("hotelid")
    private Long hotelId;

    @TableField("beddetail")
    private String bedDetail;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
