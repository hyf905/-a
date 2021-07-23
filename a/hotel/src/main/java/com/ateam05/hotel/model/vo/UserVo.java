package com.ateam05.hotel.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

@Data
public class UserVo {
    private String userName;
    private String roleCode;
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long  id;
}
