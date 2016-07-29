package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Cart;
import com.mabao.admin.repository.CartRepository;
import com.mabao.admin.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购物车业务
 * Created by jackie on 2016/07/06.
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;


    /**
     * 删除购物车内商品
     * @param cartId           购物车ID
     * @return                  结果VO
     */
    @Override
    public JsonResultVO deleteCartGoods(Long cartId) {
        try{
            this.cartRepository.delete(cartId);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

    /**
     * get一条购物车信息
     * @param cartId            购物车ID
     * @return                  购物车对象
     */
    @Override
    public Cart get(Long cartId) {
        return this.cartRepository.findOne(cartId);
    }


    /**
     * 更新购物车信息
     * @param cart              购物车
     * @return                  更新的购物车
     */
    @Override
    public Cart updateCart(Cart cart) {
        return this.cartRepository.saveAndFlush(cart);
    }


}
