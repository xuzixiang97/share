package com.xuzi.share.entity.response;

import lombok.Data;

/**
 * UserCartResponse
 *
 * @author <a href="mailto:zixiang.xu@yunhutech.com">huohe</a>
 * @since 2019/12/04
 * <p>
 * desc：
 */
@Data
public class UserCartResponse {
    private Integer id;

    private Integer userId;

    private Integer ItemId;

    private Integer type;

    private String name;

    private String describe;

    private Integer unitprice;

    private Integer quantity;

    private String categoryId;

    private String img;

}
