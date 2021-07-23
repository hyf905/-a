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
 * 用户角色关联关系表
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUserRoleRelation对象", description="用户角色关联关系表")
public class SysUserRoleRelation extends Model<SysUserRoleRelation> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "用户id")
    private Long userId;

            @ApiModelProperty(value = "角色id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
