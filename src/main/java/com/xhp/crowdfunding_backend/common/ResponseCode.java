package com.xhp.crowdfunding_backend.common;
/**
 * 服务器返回状态码
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
public enum  ResponseCode {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"ERROR"),
    PERMISSION_DENIED(403,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(500,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}