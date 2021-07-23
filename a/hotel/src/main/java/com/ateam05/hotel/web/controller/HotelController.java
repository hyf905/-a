package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.FileDeleteUtils;
import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.common.utils.UploadUtils;
import com.ateam05.hotel.mapper.HotelMapper;
import com.ateam05.hotel.mapper.HoteltypeMapper;
import com.ateam05.hotel.model.domain.Hoteltype;
import com.ateam05.hotel.model.domain.Roomtype;
import com.ateam05.hotel.model.dto.HotelDTO;
import com.ateam05.hotel.service.CommentService;
import com.ateam05.hotel.service.RoomtypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.HotelService;
import com.ateam05.hotel.model.domain.Hotel;
import com.ateam05.hotel.common.entity.JsonResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/api/hotel")
public class HotelController {

    private final Logger logger = LoggerFactory.getLogger( HotelController.class );

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomtypeService roomtypeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    @Resource
    private HotelMapper hotelMapper;

    @Value("${server.port}")
    private int serverPort;

    // 注入配置中图片保存路径
    @Value("${user.filepath}")
    private String filePath;


    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Hotel  hotel =  hotelService.getById(id);
        //查价格
        List<Roomtype> roomtypeList = roomtypeService.find(hotel.getId(),null,null,null);
        Float min = Float.MAX_VALUE;
        for(Roomtype r : roomtypeList){
            if(r.getPrice()!=null){
                if(r.getPrice()<min){
                    min=r.getPrice();
                }
            }
        }
        if(roomtypeList.isEmpty()){
            min = 0F;
        }
        hotel.setPrice(min);
        //查询评分
        if(commentService.getByHotelId(hotel.getId()).isEmpty()){
            hotel.setAvgrate(5F);
        }else{
            hotel.setAvgrate(commentService.updateRate(hotel.getId()));
        }

        return ResultTool.success(hotel);
    }

    /**
    * 描述：根据Id删除hotel
    *
    */
    @RequestMapping("/deletehotel")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody Hotel hotel) throws Exception {

        //删除原文件
        Hotel oldHotel = hotelService.getById(hotel);
        if(oldHotel.getPhoto()!=null){
            String oldpath = oldHotel.getPhoto();
            FileDeleteUtils fileDeleteUtils = new FileDeleteUtils();
            fileDeleteUtils.fileDelete(oldpath,filePath);
        }

        return ResultTool.success(hotelService.removeById(hotel.getId()));
    }


    /**
    * 描述：根据Id 更新hotel(无文件)
    *
    */
    @RequestMapping("/updatehotel")
    @ResponseBody
    public JsonResponse updateHotel(@RequestBody Hotel  hotel) throws Exception {
//        hotel.setId(hotel.getId());
        //保留原图片
        Hotel oldHotel  = hotelService.getById(hotel.getId());
        hotel.setPhoto(oldHotel.getPhoto());

        return ResultTool.success(hotelService.updateById(hotel));
    }


    /**
     * 描述：根据Id 更新hotel(有文件)
     *
     */
    @RequestMapping("/updatehotel_file")
    @ResponseBody
    public JsonResponse updateHotelFile(@RequestPart("pic") MultipartFile multipartFile , Hotel  hotel) throws Exception {

        //删除原文件
        Hotel oldHotel = hotelService.getById(hotel);
        String oldpath = oldHotel.getPhoto();
        FileDeleteUtils fileDeleteUtils = new FileDeleteUtils();
        fileDeleteUtils.fileDelete(oldpath,filePath);

        //上传新文件
        UploadUtils uploadController = new UploadUtils(serverPort,filePath);
        String imgurl = uploadController.upload(multipartFile);
        hotel.setPhoto(imgurl);


        return ResultTool.success(hotelService.updateById(hotel));
    }



    /**
     * 搜索酒店
     */
    @RequestMapping("/findhotel")
    @ResponseBody
    public JsonResponse find(@RequestBody HotelDTO hotelDTO){

        List<Hotel> hotelList = hotelService.find(hotelDTO);
        //查询酒店房型，将最低价当作酒店价格
        for(Hotel h : hotelList){
            List<Roomtype> roomtypeList = roomtypeService.find(h.getId(),null,null,null);
            Float min = Float.MAX_VALUE;
            for(Roomtype r : roomtypeList){
                if(r.getPrice()!=null){
                    if(r.getPrice()<min){
                        min=r.getPrice();
                    }
                }
            }
            if(roomtypeList.isEmpty()){
                min = 0F;
            }
            h.setPrice(min);
            //查询评分
            if(commentService.getByHotelId(h.getId()).isEmpty()){
                h.setAvgrate(5F);
            }else{
                h.setAvgrate(commentService.updateRate(h.getId()));
            }

        }

        return ResultTool.success(hotelList);
    }

    /**
     * 添加酒店(有文件)
     */
    @RequestMapping("/addhotel_file")
    @ResponseBody
    public JsonResponse addBook(Hotel hotel,@RequestPart("pic") MultipartFile multipartFile){
        UploadUtils uploadController = new UploadUtils(serverPort,filePath);
        String imgurl = uploadController.upload(multipartFile);
        hotel.setPhoto(imgurl);
        return ResultTool.success(hotelService.save(hotel));
    }


    /**
     * 添加酒店(无文件)
     */
    @RequestMapping("/addhotel")
    @ResponseBody
    public JsonResponse addBook(@RequestBody Hotel hotel){

        hotelService.save(hotel);

        QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Hotel::getHotelNameCn, hotel.getHotelNameCn());
        Hotel result = hotelMapper.selectOne(queryWrapper);

        return ResultTool.success(result.getId());
    }
}

