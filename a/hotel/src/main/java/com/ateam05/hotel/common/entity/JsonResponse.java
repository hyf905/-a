package com.ateam05.hotel.common.entity;

import com.ateam05.hotel.common.enums.ResultCode;
import com.ateam05.hotel.common.utils.ResultTool;

import java.io.Serializable;

/**
 * @Author: Huyufan
 * @Description: 统一返回实体
 */
public class JsonResponse<T> implements Serializable {
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;

    public JsonResponse() {
    }

    public JsonResponse(String msg){
        this.msg=msg;
    }

    public JsonResponse(boolean success) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }

    public JsonResponse(boolean success,String msg) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = msg;
    }

    public JsonResponse(boolean success, ResultCode resultEnum) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.msg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
    }

    public JsonResponse(boolean success, T data) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.data = data;
    }

    public JsonResponse(boolean success, ResultCode resultEnum, T data) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.msg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer errorCode) {
        this.code = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
