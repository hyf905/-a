package com.ateam05.hotel.model.domain;

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
@ApiModel(value="HotelRoomtype对象", description="")
public class HotelRoomtype extends Model<HotelRoomtype> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long hotelid;

    private Long roomtypeid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
