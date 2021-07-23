package com.ateam05.hotel.model.vo;

import com.ateam05.hotel.model.domain.SysUser;
import lombok.Data;

@Data
public class SysUserVo extends SysUser {
    private String roleName;
}
