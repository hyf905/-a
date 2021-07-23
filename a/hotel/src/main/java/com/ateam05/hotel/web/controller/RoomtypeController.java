package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.FileDeleteUtils;
import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.common.utils.UploadUtils;
import com.ateam05.hotel.model.domain.Hotel;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.RoomtypeService;
import com.ateam05.hotel.model.domain.Roomtype;
import com.ateam05.hotel.common.entity.JsonResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
@RequestMapping("/api/roomtype")
public class RoomtypeController {

    private final Logger logger = LoggerFactory.getLogger( RoomtypeController.class );

    @Value("${server.port}")
    private int serverPort;

    // 注入配置中图片保存路径
    @Value("${user.filepath}")
    private String filePath;


    @Autowired
    private RoomtypeService roomtypeService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Roomtype  roomtype =  roomtypeService.getById(id);
        return ResultTool.success(roomtype);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/deleteroomtype")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody Roomtype roomtype){

        //删除原文件
        Roomtype oldRoomtype = roomtypeService.getById(roomtype);
        if(oldRoomtype.getPhoto()!=null){
            String oldpath = oldRoomtype.getPhoto();
            FileDeleteUtils fileDeleteUtils = new FileDeleteUtils();
            fileDeleteUtils.fileDelete(oldpath,filePath);
        }
        return ResultTool.success(roomtypeService.removeById(roomtype.getId()));
    }


    /**
    * 描述：根据Id 更新(无文件)
    *
    */
    @RequestMapping(value = "/updateroomtype")
    @ResponseBody
    public JsonResponse updateRoomtype(@RequestBody Roomtype  roomtype) throws Exception {
        //保留原图片
        Roomtype oldRoomtype  = roomtypeService.getById(roomtype.getId());
        roomtype.setPhoto(oldRoomtype.getPhoto());
        return ResultTool.success(roomtypeService.updateById(roomtype));
    }

    /**
     * 描述：根据Id 更新(有文件)
     *
     */
    @RequestMapping(value = "/updateroomtype_file")
    @ResponseBody
    public JsonResponse updateRoomtypeFile(@RequestPart("pic")MultipartFile file, Roomtype  roomtype) throws Exception {

        //删除原文件
        Roomtype oldRoomtype = roomtypeService.getById(roomtype);
        String oldpath = oldRoomtype.getPhoto();
        FileDeleteUtils fileDeleteUtils = new FileDeleteUtils();
        fileDeleteUtils.fileDelete(oldpath,filePath);

        //上传新的文件
        UploadUtils uploadController = new UploadUtils(serverPort,filePath);
        String imgurl = uploadController.upload(file);
        roomtype.setPhoto(imgurl);


        return ResultTool.success(roomtypeService.updateById(roomtype));
    }


    /**
     * 添加房型(有文件)
     * @param multipartFile
     * @param roomtype
     * @return
     */
    @RequestMapping("/addroomtype_file")
    @ResponseBody
    public JsonResponse addRoomTypeFile(@RequestPart("pic") MultipartFile multipartFile, Roomtype roomtype){
        UploadUtils uploadController = new UploadUtils(serverPort,filePath);
//        if(multipartFile!=null){
        String imgurl = uploadController.upload(multipartFile);
        roomtype.setPhoto(imgurl);
//        }

        return ResultTool.success(roomtypeService.save(roomtype));
    }

    /**
     * 添加房型（无文件）
     *
     * @param roomtype
     * @return
     */
    @RequestMapping("/addroomtype")
    @ResponseBody
    public JsonResponse addRoomType(@RequestBody Roomtype roomtype){
        return ResultTool.success(roomtypeService.save(roomtype));
    }


    /**
     * 搜索房型
     *
     * @param params(hotelId,bedtype,minPrice,maxPrice)
     * @return
     */
    @RequestMapping("/findroomtype")
    @ResponseBody
    public JsonResponse findRoomtype(@RequestBody Map<String,String> params){
//        System.out.println(params.get("maxPrice")+"---------");
        Long hotelId = 0L;
        if(params.get("hotelId")!=null && params.get("hotelId")!=""){
            hotelId = Long.parseLong(params.get("hotelId"));
        }
        String bedtype = params.get("bedtype");
        Float minPrice = Float.MIN_VALUE;
        Float maxPrice = Float.MAX_VALUE;
        if(params.get("minPrice")!=null && params.get("minPrice")!=""){
            minPrice = Float.parseFloat(params.get("minPrice"));
        }
        if(params.get("maxPrice")!=null && params.get("maxPrice")!=""){
            maxPrice = Float.parseFloat(params.get("maxPrice"));
        }
        return ResultTool.success(roomtypeService.find(hotelId,bedtype,minPrice,maxPrice));
    }
}

