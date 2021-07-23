package com.ateam05.hotel.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRole对象", description="用户角色表")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "管理code")
    private String roleCode;

            @ApiModelProperty(value = "角色名")
    private String roleName;

            @ApiModelProperty(value = "角色说明")
    private String roleDescription;

    private List<SysPermission> list;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
