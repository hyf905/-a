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
 * 路径权限关联表
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRequestPathPermissionRelation对象", description="路径权限关联表")
public class SysRequestPathPermissionRelation extends Model<SysRequestPathPermissionRelation> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
            @ApiModelProperty(value = "主键id")
    private Integer id;

            @ApiModelProperty(value = "请求路径id")
    private Integer urlId;

            @ApiModelProperty(value = "权限id")
    private Integer permissionId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
