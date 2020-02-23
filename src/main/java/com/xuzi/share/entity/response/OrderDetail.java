package com.xuzi.share.entity.response;

import com.xuzi.share.entity.Order;
import lombok.Data;

import java.util.List;
@Data
public class OrderDetail extends Order {

    private List<OrderItemDetailResponse> orderItemList;
    private String date;

}
