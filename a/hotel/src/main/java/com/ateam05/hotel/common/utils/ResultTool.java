package com.ateam05.hotel.common.utils;


import com.ateam05.hotel.common.entity.JsonResponse;
import com.ateam05.hotel.common.enums.ResultCode;

/**
 * @Author: Huyufan
 * @Description:
 */
public class ResultTool {

    public static JsonResponse success() {
        return new JsonResponse(true);
    }

    public static JsonResponse success(String msg) {
        return new JsonResponse(true,msg);
    }

    public static <T> JsonResponse<T> success(T data) {
        return new JsonResponse(true, data);
    }


    public static JsonResponse fail() {
        return new JsonResponse(false);
    }


    public static JsonResponse fail(ResultCode resultEnum){ return new JsonResponse(false, resultEnum); }


    public static JsonResponse fail(ResultCode resultEnum,String msg) {
        resultEnum.setMessage(msg);
        return new JsonResponse(false, resultEnum);
    }
}
