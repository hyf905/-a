package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.enums.ResultCode;
import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.mapper.HoteltypeMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.HoteltypeService;
import com.ateam05.hotel.model.domain.Hoteltype;
import com.ateam05.hotel.common.entity.JsonResponse;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/api/hoteltype")
public class HoteltypeController {

    private final Logger logger = LoggerFactory.getLogger( HoteltypeController.class );

    @Autowired
    private HoteltypeService hoteltypeService;

    @Autowired
    @Resource
    private HoteltypeMapper hoteltypeMapper;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Hoteltype  hoteltype =  hoteltypeService.getById(id);
        return ResultTool.success(hoteltype);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody Hoteltype hoteltype) throws Exception {

        return ResultTool.success(hoteltypeService.removeById(hoteltype.getId()));
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResponse updateHoteltype(@RequestBody Hoteltype  hoteltype) throws Exception {

        return ResultTool.success(hoteltypeService.updateById(hoteltype));
    }


    /**
    * 描述:添加Hoteltype
    *
    */
    @RequestMapping(value = "/addhoteltype", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addHotelType(@RequestBody Hoteltype  hoteltype) throws Exception {
        QueryWrapper<Hoteltype> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Hoteltype::getTypename, hoteltype.getTypename());
        Hoteltype result = hoteltypeMapper.selectOne(queryWrapper);
        if(result!=null){
            return ResultTool.fail(ResultCode.PARAM_REPEAT,"酒店类型名重复");
        }else {
            return ResultTool.success(hoteltypeService.save(hoteltype));
        }
    }


    /**
     * 查询所有酒店类型
     * @return
     */
    @RequestMapping("/findall")
    @ResponseBody
    public JsonResponse findAll(){
        return ResultTool.success(hoteltypeService.list());
    }


    /**
     * 查询（逻辑）未删除的酒店类型
     * @return
     */
    @RequestMapping("/notdeleted")
    @ResponseBody
    public JsonResponse selectByIsdeleted(){
        return ResultTool.success(hoteltypeService.selectIsdeleted());
    }


}

