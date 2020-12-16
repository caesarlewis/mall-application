package com.jasonless.mall.api.goods.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    // Spu
    private Spu spu;
    // Sku
    private List<Sku> skus;

}
