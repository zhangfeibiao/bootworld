package com.zfb.bootworld.system.enums;


public enum ErrorCodeEnum {

    /**
     * 系统异常
     */
    BAD_REQUEST("400", "请求参数有误！"),

    NOT_FOUND("404", "请求的资源不存在"),

    METHOD_NOT_ALLOWED("405", "接口请求方法类型错误"),

    NOT_ACCEPTABLE("406", "服务端不支持生成指定的响应实体！"),

    UNSUPPORTED_MEDIA_TYPE("415", "服务端不支持请求中提交的实体格式！"),

    JSON_CONVERT_ERROR("452", "json格式错误！"),

    INTERNAL_SERVER_ERROR("500", "服务繁忙,请稍后再试!"),


    /**
     * 自定义异常
     */

    ILLEGAL_OPERATION("2000", "非法操作！"),

    SCHEDULE_CANNOT_ORDER("2001", "当前排班不可预约"),

    BLACKLIST_REJECT("2002", "您违约次数过多不能再次预约，请待系统评定后再次尝试！"),

    PHONE_NUMBER_ERROR("2003", "请输入正确的手机号"),

    ID_CARD_ERROR("2004", "非法身份证号"),

    SMS_CODE_ERROR("20005", "短信验证码错误"),

    REQUEST_IMAGE_CODE("20006", "请输入图形验证码"),

    IMAGE_CODE_ERROR("20007", "图形验证码错误"),

    SMS_TEMPLATE_IS_NOT_EXIST("20008", "短信模板不存在"),

    USER_NOT_LOGIN("20009", "用户未登录"),

    DO_NOT_SUBMIT_AGAIN("20010", "请勿重复提交"),

    OPERATION_FREQUENTLY("20011", "请求频繁，请勿重复请求"),

    FREQUENT_OPERATION("20012", "两次发送短信间隔不能少于60秒"),

    USER_TOKEN_EXPIRE("20013", "Token失效请重新登录"),

    DUPLICATE_USER_ORDER("20014", "您已经有当前排班的预约哦！"),

    DUPLICATE_PATIENT("20015", "您已经存在相同就诊人哦！"),

    ;

    ErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private String errorCode;

    private String errorMsg;

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

}
