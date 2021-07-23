package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.HotelRoomtypeService;
import com.ateam05.hotel.model.domain.HotelRoomtype;
import com.ateam05.hotel.common.entity.JsonResponse;

/**
 *
 *  前端控制器
 *
 *
 * @author hyf
 * @since 2021-06-17
 * @version v1.0
 */
@Controller
@RequestMapping("/api/hotelRoomtype")
public class HotelRoomtypeController {

    private final Logger logger = LoggerFactory.getLogger( HotelRoomtypeController.class );

    @Autowired
    private HotelRoomtypeService hotelRoomtypeService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        HotelRoomtype  hotelRoomtype =  hotelRoomtypeService.getById(id);
        return ResultTool.success(hotelRoomtype);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        hotelRoomtypeService.removeById(id);
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateHotelRoomtype(@PathVariable("id") Long  id,HotelRoomtype  hotelRoomtype) throws Exception {
        hotelRoomtype.setId(id);
        hotelRoomtypeService.updateById(hotelRoomtype);
        return ResultTool.success(null);
    }


    /**
    * 描述:创建HotelRoomtype
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(HotelRoomtype  hotelRoomtype) throws Exception {
        hotelRoomtypeService.save(hotelRoomtype);
        return ResultTool.success(null);
    }
}

