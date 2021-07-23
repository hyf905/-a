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
 * 角色-权限关联关系表
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRolePermissionRelation对象", description="角色-权限关联关系表")
public class SysRolePermissionRelation extends Model<SysRolePermissionRelation> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "角色id")
    private Integer roleId;

            @ApiModelProperty(value = "权限id")
    private Integer permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
