package com.ateam05.hotel.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 请求路径
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRequestPath对象", description="请求路径")
public class SysRequestPath extends Model<SysRequestPath> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "请求路径")
    private String url;

            @ApiModelProperty(value = "路径描述")
    private String description;

    private String method;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
