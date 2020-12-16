package com.jasonless.mall.service.cart.mapper;

import com.jasonless.mall.api.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jasonless
 * @date 2020/12/11
 */
@Repository
public interface CartMapper extends MongoRepository<Cart,String> {
}
