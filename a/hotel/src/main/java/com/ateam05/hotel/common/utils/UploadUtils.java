package com.ateam05.hotel.common.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class UploadUtils {

//    @Value("${server.port}")
    private int serverPort;

    // 注入配置中图片保存路径
    private String filePath;

    public UploadUtils(int serverPort, String filePath){
        this.serverPort = serverPort;
        this.filePath =filePath;
    }

    // 处理上传图片请求的方法
    // @RequestPart("pic")MultipartFile 上传文件时携带图片的key定义为pic
//    @RequestMapping(value = "/img",method = RequestMethod.POST,consumes = "multipart/form-data")
//    @ResponseBody
    public String upload( MultipartFile multipartFile){

        // 生成一个随机的名称，避免文件名重复
        UUID uuid = UUID.randomUUID();
        // 获取原文件名称
        String originalFileName = multipartFile.getOriginalFilename();
        // 获取原文件的后缀
        String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
        // 保存文件
        File file = new File(filePath + uuid + fileSuffix);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        //动态获取地址和端口
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
//            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //address.getHostAddress() 地址
        // 返回图片的完整访问路径，这地方ip和端口可以改为动态获得，这样在部署到服务器上时无需改变，为了方便起见这里直接写死了
        return "http://"+address.getHostAddress()+":"+this.serverPort+"/api/images/"+uuid+fileSuffix;
    }

}
