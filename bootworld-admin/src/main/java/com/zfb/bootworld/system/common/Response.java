package com.zfb.bootworld.system.common;

import com.zfb.bootworld.system.enums.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Response
 * @Description: 响应JSON结构
 * @Author: zhangfeibiao
 * @CreateDate: 2018/11/19 16:54
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer success = 1;
    private String errorCode;
    private String errorMsg;
    private T data;
    private String traceId;

    public Response() {
    }

    public Response(Integer success, String errorCode, String errorMsg, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }


    public static <T> Response<T> success() {
        return new Response(1, null, null, "SUCCESS");
    }

    public static <T> Response<T> success(T data) {
        return new Response(1, null, null, data);
    }

    public static <T> Response<T> failure(T data) {
        return new Response(0, null, null, data);
    }

    public static <T> Response failure(ErrorCodeEnum errorCodeEnum) {
        return new Response(0, errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorMsg(), "FAILURE");
    }

    public static <T> Response failure(ErrorCodeEnum errorCodeEnum, T data) {
        return new Response(0, errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorMsg(), data);
    }

    public static <T> Response<T> failure(String errorCode, String errorMsg) {
        return new Response(0, errorCode, errorMsg, "FAILURE");
    }

    public static <T> Response<T> failure(String errorCode, String errorMsg, Object data) {
        return new Response(0, errorCode, errorMsg, data);
    }


}
