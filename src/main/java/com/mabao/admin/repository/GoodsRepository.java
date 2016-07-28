package com.mabao.admin.repository;


import com.mabao.admin.pojo.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends BaseRepository<Goods>{

    /**
     * 通过搜索条件查询商品
     * @param goodsTypeId             商品类别id
     * @param state                   是否上架
     * @param title                   商品名称
     * @param articleNumber           货号
     * @return
     */
    List<Goods> findByTypeNameOrStateOrTitleOrArticleNumber(Long goodsTypeId, Boolean state,String title,String articleNumber);




}
