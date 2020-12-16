package com.jasonless.mall.api.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * @author Jasonless
 * @date 2020/12/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    private String _id;
    private String userName;
    private String name;
    private Integer price;
    private String image;
    private String skuId;
    private Integer num;

}
