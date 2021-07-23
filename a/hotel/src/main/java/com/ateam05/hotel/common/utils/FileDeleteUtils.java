package com.ateam05.hotel.common.utils;

import lombok.Data;

import java.io.File;

@Data
public class FileDeleteUtils {

    /**
     * 删除文件
     * @param viewFilePath  访问地址，即存在数据库中的地址
     * @param trueFilePath  存放地址，文件真实存放的地址
     */
    public void fileDelete(String viewFilePath,String trueFilePath){
        int lastIndexOf = viewFilePath.lastIndexOf("/");
        String oldFilePath = viewFilePath.substring(lastIndexOf + 1, viewFilePath.length());
        oldFilePath = trueFilePath + oldFilePath;
        File oldfile = new File(oldFilePath);
        if (oldfile.exists()) {//文件是否存在
            oldfile.delete();
        }
    }
}
