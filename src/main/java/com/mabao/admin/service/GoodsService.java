package com.mabao.admin.service;

import com.mabao.admin.controller.vo.GoodsInVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Goods;
import org.springframework.data.domain.Page;

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
     * @param goodsInVO        商品对象，需包含用户ID
     * @return                保存的商品对象
     */
    Goods saveGoods(GoodsInVO goodsInVO);

    /**
     * 新建商品
     * @param goodsInVO         商品对象，需包含用户ID
     * @return              保存的商品对象
     */
    Goods newGoods(GoodsInVO goodsInVO);

    /**
     * 获取所有商品信息
     * @return
     */
    Page<Goods> getAllGoods(int page, int pageSize);

    /**
     * 根据商品ID查商品list
     * @param goodsIdList           商品ID集合
     * @return                      商品list
     */
    List<Goods> findGoodsByIdIn(String goodsIdList);

    /**
     * 删除商品
     * @param goodsId               删除商品的id
     */
    JsonResultVO deleteGoods(Long goodsId);


    /**
     * 根据需求查询商品
     * @param goodsTypeId                     商品类别
     * @param state                         商品状态
     * @param title                         商品名称
     * @param articleNumber                 商品货号
     * @param page                          页数
     * @param pageSize                      每页大小
     * @return
     */
    Page<Goods> selectGoods(Long goodsTypeId, Boolean state,
                            String title, String articleNumber,
                            int page, int pageSize);

    /**
     * 删除选中的商品
     * @param ids               多个商品id的字符串
     */
    JsonResultVO deleteSomeGoods(String ids);

    /**
     * 根据穿额度状态改变一些商品的状态
     * @param ids               需要改变状态商品的id集合字符串
     * @param state             需要改成的状态值
     */
    JsonResultVO changeGoodsState(String ids, Boolean state);
}
