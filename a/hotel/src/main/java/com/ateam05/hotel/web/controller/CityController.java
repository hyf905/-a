package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.CityService;
import com.ateam05.hotel.model.domain.City;
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
@RequestMapping("/api/city")
public class CityController {

    private final Logger logger = LoggerFactory.getLogger( CityController.class );

    @Autowired
    private CityService cityService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        City  city =  cityService.getById(id);
        return ResultTool.success(city);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        cityService.removeById(id);
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateCity(@PathVariable("id") Long  id,City  city) throws Exception {
        city.setId(id);
        cityService.updateById(city);
        return ResultTool.success(null);
    }


    /**
    * 描述:创建City
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(City  city) throws Exception {
        cityService.save(city);
        return ResultTool.success(null);
    }
}

