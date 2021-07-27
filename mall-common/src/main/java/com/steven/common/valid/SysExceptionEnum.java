package com.steven.common.valid;

/**
 * 系统全部异常枚举
 * @author steven
 * @desc
 * @date 2021/2/7 16:45
 */
public enum SysExceptionEnum {

    /**
     * 系统未知异常，获取的Throwable
     */
    UNKNOWN_EXCEPTION(5000,"系统未知异常"),
    /**
     * 参数校验异常
     */
    VALID_EXCEPTION(5100,"参数异常"),
    /**
     * 订单异常
     */
    ORDER_EXCEPTION(5200,"订单异常"),
    /**
     * 仓储异常
     */
    WARE_EXCEPTION(5300,"仓储异常"),
    /**
     * 支付异常
     */
    PAY_EXCEPTION(5400,"支付异常"),
    /**
     * 优惠服务异常
     */
    COUPON_EXCEPTION(5500,"优惠服务异常"),
    /**
     * 会员异常
     */
    MEMBER_EXCEPTION(5600,"会员异常");

    private int code;
    private String msg;

    SysExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
