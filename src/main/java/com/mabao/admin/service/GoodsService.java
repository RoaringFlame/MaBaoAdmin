package com.mabao.admin.service;

import com.mabao.admin.pojo.Goods;

import java.util.List;


/**
 * Created by liuming on 2016/6/28.
 * 商品业务接口
 */
public interface GoodsService {

    /**
     * 查询商品详细信息
     * @param goodsId           商品ID
    */
    Goods get(Long goodsId);

    /**
     * 保存商品
     * @param goods        商品对象，需包含用户ID
     * @return                保存的商品对象
     */
    Goods saveOne(Goods goods);

    /**
     * 根据商品ID查商品list
     * @param goodsIdList           商品ID集合
     * @return                      商品list
     */
    List<Goods> findGoodsByIdIn(List<Long> goodsIdList);

}
