package com.ateam05.hotel.model.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    // @TableId(type = IdType.ASSIGN_ID)
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "账号")
        private String account;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "上一次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "账号是否可用。默认为1（可用）")
    private Boolean enabled;

    @TableField("is_deleted")
    @TableLogic//开启逻辑删除
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
