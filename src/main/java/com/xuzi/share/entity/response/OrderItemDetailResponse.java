package com.xuzi.share.entity.response;

import lombok.Data;

/**
 * OrderItemDetailPreponse
 *
 * @author <a href="mailto:zixiang.xu@yunhutech.com">huohe</a>
 * @since 2019/12/04
 * <p>
 * desc：
 */
@Data
public class OrderItemDetailResponse {

    private Integer id;

    private Integer orderId;

    private Integer ItemId;

    private String ItemName;

    private Integer unitprice;

    private String img;
}
