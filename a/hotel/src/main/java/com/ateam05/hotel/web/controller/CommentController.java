package com.ateam05.hotel.web.controller;

import com.ateam05.hotel.common.utils.ResultTool;
import com.ateam05.hotel.common.utils.UploadUtils;
import com.ateam05.hotel.model.dto.CommentDTO;
import com.ateam05.hotel.service.SysUserService;
import io.swagger.annotations.ApiImplicitParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.CommentService;
import com.ateam05.hotel.model.domain.Comment;
import com.ateam05.hotel.common.entity.JsonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
@RequestMapping("/api/comment")
public class CommentController {

    private final Logger logger = LoggerFactory.getLogger( CommentController.class );

    @Value("${server.port}")
    private int serverPort;

    // 注入配置中图片保存路径
    @Value("${user.filepath}")
    private String filePath;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SysUserService sysUserService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Comment  comment =  commentService.getById(id);
        return ResultTool.success(comment);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        commentService.removeById(id);
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateComment(@PathVariable("id") Long  id,Comment  comment) throws Exception {
        comment.setId(id);
        commentService.updateById(comment);
        return ResultTool.success(null);
    }
    /**
     * 描述：更新酒店评分
     *
     */
    @RequestMapping(value = "/updateRate/{hotelId}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse updateRate(@PathVariable("hotelId") Long  hotelId) throws Exception {
        float avg = commentService.updateRate(hotelId);
        return ResultTool.success(avg);
    }


    /**
    * 描述:创建Comment（有文件）
    *
    */
    @RequestMapping(value = "/savecomment_file", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse createFile(@RequestPart("pic") MultipartFile multipartFile,Comment  comment) throws Exception {
        UploadUtils uploadController = new UploadUtils(serverPort,filePath);
//        if(multipartFile!=null){
        String imgurl = uploadController.upload(multipartFile);
        comment.setPhoto(imgurl);
        comment.setDate(new Date());
        commentService.save(comment);
        return ResultTool.success(null);
    }
    /**
     * 描述:创建Comment(无文件)
     *
     */
    @RequestMapping(value = "/savecomment", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Comment  comment) throws Exception {
        comment.setDate(new Date());
        commentService.save(comment);
        return ResultTool.success(null);
    }

    /**
     * 根据酒店id查询
     * @param hotelId
     * @return
     */
    @RequestMapping(value = "/getbyhotelid", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse getByHotelId(@Param("hotelId") Long hotelId){
        List<CommentDTO> commentDTOList = commentService.getByHotelId(hotelId);
        for(CommentDTO c: commentDTOList){
            c.setUsername(sysUserService.getById(c.getUserId()).getUserName());
        }
        return ResultTool.success(commentDTOList);
    }
}

