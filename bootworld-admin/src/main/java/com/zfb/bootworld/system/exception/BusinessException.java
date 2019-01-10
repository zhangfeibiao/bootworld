package com.zfb.bootworld.system.exception;


import com.zfb.bootworld.system.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * @author zhangfeibiao
 * 继承运行时异常
 */
@Data
public class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5110371239340231002L;

    private String errorCode;

    private String errorMsg;

    private Object data;

    private Throwable cause;

    public BusinessException(Throwable cause) {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.errorMsg = message;

    }

    public BusinessException(ErrorCodeEnum errorCode, Throwable cause) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
        this.cause = cause;
    }

    public BusinessException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
    }

    public BusinessException(ErrorCodeEnum errorCode, Object data) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
        this.data = data;
    }

}
