package com.jasonless.mall.common.util;

/**
 * @author Jasonless
 * @date 2020/10/24
 */
public enum ResponseCode {

    /**
     * 操作返回枚举类型
     */

    SUCCESS(20000, "操作成功"),
    ERROR(50000, "操作失败"),
    SYSTEM_ERROR(50001, "系统错误");

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    ResponseCode() {
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
