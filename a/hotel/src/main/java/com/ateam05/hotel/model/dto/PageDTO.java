package com.ateam05.hotel.model.dto;
import lombok.Data;

@Data
public class PageDTO {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Integer total ;
}
