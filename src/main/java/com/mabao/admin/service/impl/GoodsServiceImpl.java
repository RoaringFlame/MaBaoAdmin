package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.enums.State;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.repository.GoodsRepository;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 查询商品信息
     */
    @Override
    public Goods get(Long goodsId) {
        return this.goodsRepository.findOne(goodsId);
    }

    /**
     * 保存商品
     * @param newGoods        商品对象，需包含用户ID
     * @return                保存的商品对象
     */
    @Override
    public Goods saveGoods(Goods newGoods) {
        return this.goodsRepository.save(newGoods);
    }

    /**
     * 新建商品
     * @param goods         商品对象，需包含用户ID
     * @return              保存的商品对象
     */
    @Override
    public Goods newGoods(Goods goods) {
        return this.goodsRepository.save(goods);
    }

    /**
     * 获取所有商品信息
     * @return
     */
    @Override
    public Page<Goods> getAllGoods(int page, int pageSize) {
        return this.goodsRepository.findAll(new PageRequest(page, pageSize));
    }

    /**
     * 根据商品ID查商品list
     * @param goodsIdList           商品ID集合
     * @return                      商品list
     */
    @Override
    public List<Goods> findGoodsByIdIn(String goodsIdList) {
        return this.goodsRepository.findByIdIn(goodsIdList);
    }

    /**
     * 删除商品
     * @param goodsId               删除商品的id
     */
    @Override
    public JsonResultVO deleteGoods(Long goodsId) {
        try{
            this.goodsRepository.delete(goodsId);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }


    /**
     * 查询商品
     * @param goodsTypeId                   商品类别
     * @param state                         商品状态
     * @param title                         商品名称
     * @param articleNumber                 商品货号
     * @param page                          页数
     * @param pageSize                      每页大小
     * @return
     */
    @Override
    public Page<Goods> selectGoods(Long goodsTypeId, Boolean state, String title, String articleNumber, int page, int pageSize) {
        return this.goodsRepository.findByTypeIdAndStateAndTitleAndArticleNumber(goodsTypeId,state,title,articleNumber,new PageRequest(page, pageSize));
    }

    /**
     * 删除选中的商品
     * @param ids               多个商品id的字符串
     */
    @Override
    public JsonResultVO deleteSomeGoods(String ids) {
        try{
            String[] strs = ids.split(",");
            for(String goodsId:strs) {
                this.goodsRepository.delete(Long.decode(goodsId));
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

    @Override
    public JsonResultVO changeGoodsState(String ids, Boolean state) {
        try{
            Goods goods;
            String[] strs = ids.split(",");
            for(String goodsId:strs) {
                goods = this.goodsRepository.findOne(Long.valueOf(goodsId));
                goods.setState(state);
                this.goodsRepository.saveAndFlush(goods);
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
       return new JsonResultVO(JsonResultVO.SUCCESS,"修改成功！");
    }


}
