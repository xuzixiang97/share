package com.xuzi.share.constant;

public class OrderStatus {

    /**
     * 初始化
     */
    public static Integer INIT = 1;

    /**
     * 待支付
     */
    public static Integer WAIT_PAY = 2;

    /**
     * 已支付
     */
    public static Integer PAID = 3;

    /**
     * 退款中
     */
    public static Integer REFUNDING = 4;

    /**
     * 退款失败
     */
    public static Integer UNREFUND = 5;

    /**
     * 退款成功
     */
    public static Integer REFUNDED = 6;



}
