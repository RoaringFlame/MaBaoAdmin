package com.mabao.admin.service;


import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Cart;

/**
 * 购物车业务接口
 * Created by jackie on 2016/07/06.
 */
public interface CartService {

    /**
     * 删除购物车内商品
     * @param cartId           购物车ID
     * @return                  结果VO
     */
    JsonResultVO deleteCartGoods(Long cartId);

    /**
     * get一条购物车信息
     * @param cartId            购物车ID
     * @return                  购物车对象
     */
    Cart get(Long cartId);

    /**
     * 更新购物车信息
     * @param cart              购物车
     * @return                  更新的购物车
     */
    Cart updateCart(Cart cart);

}
