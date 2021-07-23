package com.ateam05.hotel.model.dto;

import lombok.Data;

@Data
public class HotelDTO {
    private String hotelNameCn;
    private String city;
    private Float starRating;
    private Long hoteltypeid;
    private String bedtype;
}
