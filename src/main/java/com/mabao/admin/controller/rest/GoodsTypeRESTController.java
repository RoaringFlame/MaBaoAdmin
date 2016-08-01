package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.enums.Role;
import com.mabao.admin.enums.State;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lies on 2016/7/30.
 */
@RestController
@RequestMapping(value = "/goodsType")
public class GoodsTypeRESTController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 根据传入商品类型id删除相应的商品类型
     * @param ids                   传入的商品类型id的集合字符串
     */
    @RequestMapping(value = "/deleteSomeGoodsType",method = RequestMethod.GET)
    public JsonResultVO deleteSomeGoods(String ids) {
        try{
            String[] strs = ids.split(",");
            for(String id:strs) {
                goodsTypeService.deleteGoodsType(Long.valueOf(id));
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

    /**
     * 显示所有商品类别
     * @return              goodsType页面显示
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonResultVO showGoodsType(int page, int pageSize,Model model) {
        try{
            Map<String,Object> map = new HashMap<>();
            //登陆用户角色
            List<Selector> role = Role.toList();
            map.put("role",role);
            //页面显示信息
            Page<GoodsType> goodsList = this.goodsTypeService.getAllGoodsType(page,pageSize);
            map.put("allGoodsType",goodsList);
            model.addAllAttributes(map);

        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"显示成功！");
    }

    /**
     * 新增商品类别
     * @param goodsType
     * @return               结果VO
     */
    @RequestMapping(value = "/addGoodsType",method = RequestMethod.GET)
    public JsonResultVO AddGoodsType(@RequestParam GoodsType goodsType){
        return this.goodsTypeService.addGoodsType(goodsType);
    }

    /**
     * 删除商品类别
     * @return              结果VO
     */
    @RequestMapping(value = "/deleteGoodsType",method = RequestMethod.GET)
    public JsonResultVO AddGoodsType(@RequestParam Long goodsTypeId) {
        return this.goodsTypeService.deleteGoodsType(goodsTypeId);
    }


}
