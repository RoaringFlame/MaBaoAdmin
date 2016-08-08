package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.GoodsInVO;
import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.enums.BabyType;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsBrand;
import com.mabao.admin.repository.BaseDao;
import com.mabao.admin.repository.GoodsRepository;
import com.mabao.admin.service.GoodsBrandService;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsBrandService goodsBrandService;
    @Autowired
    private UserService userService;
    @Autowired
    private BaseDao baseDao;
    /**
     * 查询商品信息
     */
    @Override
    public Goods get(Long goodsId) {
        return this.goodsRepository.findOne(goodsId);
    }

    /**
     * 保存商品
     * @param goodsInVO        商品对象，需包含用户ID
     * @return                保存的商品对象
     */
    @Override
    public Goods saveGoods(GoodsInVO goodsInVO) {
        Goods goods = this.goodsRepository.findOne(goodsInVO.getId());
        goods.setTitle(goodsInVO.getTitle());                                   //商品名称
        goods.setPrice(goodsInVO.getPrice());                                   //价格
        goods.setNewDegree(goodsInVO.getNewDegree());                           //新旧级别
        goods.setMessage(goodsInVO.getMessage());                               //商品介绍
        goods.setUpTime(new Date());                                            //上传时间呗设定为购买时间
        goods.setId(goodsInVO.getId());

        /*goods.setUser(this.userService.get(1L));            //userId
        goods.setOldPrice(new Double(20));                                      //旧的价格
        goods.setState(true);                                                   //设置状态为true
        GoodsBrand brand =this.goodsBrandService.get(8L);                                   //设置假数据
        goods.setBrand(brand);                                                  //设置商品品牌
        goods.setBrandName(brand.getBrandName());                             //设置品牌名称
        goods.setBabyType(BabyType.MEN);                                        //设置适合宝宝为男*/
        return this.goodsRepository.saveAndFlush(goods);
    }

    /**
     * 新建商品
     * @param goodsInVO         商品对象，需包含用户ID
     * @return              保存的商品对象
     */
    @Override
    public Goods newGoods(GoodsInVO goodsInVO) {
        Goods goods = new Goods();
        goods.setTitle(goodsInVO.getTitle());                                   //商品名称
        goods.setPrice(goodsInVO.getPrice());                                   //价格
        goods.setNewDegree(goodsInVO.getNewDegree());                           //新旧级别
        goods.setMessage(goodsInVO.getMessage());                               //商品介绍
        goods.setUpTime(new Date());                                            //上传时间呗设定为购买时间

        goods.setUser(this.userService.get(1L));                                //userId
        goods.setOldPrice(new Double(20));                                      //旧的价格
        goods.setState(true);                                                   //设置状态为true
        GoodsBrand brand =this.goodsBrandService.get(8L);                       //设置假数据
        brand.setBrandName(brand.getBrandName());
        goods.setBrand(brand);                                                  //设置商品品牌
        goods.setBrandName(brand.getBrandName());                               //设置品牌名称
        goods.setBabyType(BabyType.MEN);                                        //设置适合宝宝为男
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
    public PageVO<GoodsVO> selectGoods(Long goodsTypeId, Boolean state, String title, String articleNumber, int page, int pageSize) {
        String JPQL = "select g from Goods g "+
                "inner join fetch g.type gt " ;
        String JPQLCount = "select count(g) from Goods g ";
        StringBuilder str = new StringBuilder("where 1 = 1 ");
        List<Object> args = new ArrayList<>();

        if (goodsTypeId !=null) {
            args.add(goodsTypeId);
            str.append(" and g.type.id = ?");
            str.append(args.size());
        }
        if (state != null ) {
            args.add(state);
            str.append(" and g.state = ?");
            str.append(args.size());
        }
        if (title != null && !"".equals(title)) {
            args.add("%"+title+"%");
            str.append(" and g.title like ?");
            str.append(args.size());
        }
        if (articleNumber != null && !"".equals(articleNumber)) {
            args.add("%"+articleNumber+"%");
            str.append(" and g.articleNumber like ?");
            str.append(args.size());
        }

        String jpqlAll = JPQL + str.toString();
        String count = JPQLCount + str.toString();
        //分页时从第0页算起
        PageVO<Goods> pages = this.baseDao.findAll(jpqlAll,count,args.toArray(),page-1,pageSize);
        PageVO<GoodsVO> pageVO = new PageVO<>();
        pageVO.setItems(GoodsVO.generateBy(pages.getItems()));
        pageVO.setCurrentPage(pages.getCurrentPage()+1);
        pageVO.setTotalRow(pages.getTotalRow());
        pageVO.setPageSize(pages.getPageSize());
        return pageVO;
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

    /**
     * 改变选择商品的状态
     * @param ids               需要改变状态商品的id集合字符串
     * @param state             需要改成的状态值
     * @return
     */
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
