package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.*;
import com.mabao.admin.enums.BabyType;
import com.mabao.admin.enums.GoodsState;
import com.mabao.admin.enums.Quality;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.PageVO;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/goods")
public class GoodsRESTController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 根据需求查询商品
     * @param goodsTypeId   商品类别
     * @param state         商品状态
     * @param title         商品名称
     * @param articleNumber 商品货号
     * @param page          页数
     * @param pageSize      每页大小
     * @return              PageVO<GoodsVO>
     */
    @RequestMapping(value = "/searchGoods", method = RequestMethod.GET)
    public PageVO<GoodsVO> showSelectGoods(@RequestParam(required = false) Long goodsTypeId,
                                           @RequestParam(required = false) Boolean state,
                                           @RequestParam(required = false) String title,
                                           @RequestParam(required = false) String articleNumber,
                                           @RequestParam(required = false,defaultValue = "1") int page,
                                           @RequestParam(required = false,defaultValue = "8") int pageSize) {
        return this.goodsService.selectGoods(goodsTypeId, state, title, articleNumber, page, pageSize);
    }

    /**
     * 通过传入商品id删除商品
     * @param ids 删除商品集合的字符串
     */
    @RequestMapping(value = "/deleteSomeGoods", method = RequestMethod.GET)
    public JsonResultVO deleteSomeGoods(@RequestParam String ids) {
        return this.goodsService.deleteSomeGoods(ids);
    }

    /**
     * 改变选择商品状态
     * @param ids   选择商品id
     * @param state 需要改成的状态
     */
    @RequestMapping(value = "/changeSomeGoods", method = RequestMethod.GET)
    public JsonResultVO changeSomeGoods(String ids, Boolean state) {
        return this.goodsService.changeGoodsState(ids, state);
    }

    /**
     * 获得商品信息
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoods", method = GET)
    public GoodsInVO getGoods(@RequestParam Long goodsId) {
        return GoodsInVO.generateBy(this.goodsService.get(goodsId));
    }

    /**
     * 修改商品信息
     * @param goodsInVO             传入商品
     */
    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public JsonResultVO updateGoods(@RequestBody GoodsInVO goodsInVO) {
        try{
            this.goodsService.saveGoods(goodsInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"修改成功！");
    }

    /**
     * 添加商品
     * @param goodsInVO
     * @return
     */
    @RequestMapping(value = "/addGoods",method = POST)
    public JsonResultVO addGoods(@RequestBody GoodsInVO goodsInVO) {
        try{
            this.goodsService.newGoods(goodsInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"添加成功！");
    }

    /**
     * 获得所有商品信息
     * @param page
     * @param pageSize
     * @return
     *//*
    @RequestMapping(value = "/goodsList",method = GET)
    public PageVO<GoodsVO> goodsList(int page, int pageSize) {
        Page<Goods> pageGoods = this.goodsService.getAllGoods(page,pageSize);
        PageVO<GoodsVO> voPage = new PageVO<>();
        voPage.toPage(pageGoods);
        voPage.setItems(GoodsVO.generateBy(pageGoods.getContent()));
        return voPage;
    }*/

    /**
     * goods页面初始化下拉框
     * @return
     */
    @RequestMapping(method = GET)
    public GoodsInitVO GoodsInit() {
        GoodsInitVO goodsInitVO = new GoodsInitVO();
        //商品类别
        List<Selector> goodsTypeList =  this.goodsTypeService.getAllGoodsTypeForSelector();
        goodsInitVO.setGoodsTypeList(goodsTypeList);
        //新旧级别
         List<Selector>  newDegreeList = Quality.toList();
        goodsInitVO.setNewDegreeList(newDegreeList);
        //商品上下架状态
        List<Selector>  stateList = GoodsState.toList();
        goodsInitVO.setStateList(stateList);
        //适合宝贝类别
        List<Selector>  babyTypeList = BabyType.toList();
        goodsInitVO.setBabyTypeList(babyTypeList);
        return goodsInitVO;
    }

    /**
     *批量导出商品数据
     * @param goodsTypeId   商品类别id
     * @param state         商品状态
     * @param title         商品名称
     * @param articleNumber 商品货号
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bulkExport",method = GET)
    public JsonResultVO bulkExportGoods(@RequestParam(required = false) Long goodsTypeId,
                                        @RequestParam(required = false) Boolean state,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String articleNumber,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            this.goodsService.exportDataGoodsDetail(request,response,goodsTypeId,state,title,articleNumber);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"导出成功！");
    }

    /**
     * 批量导入商品数据
     * @param request
     * @return
     */
    @RequestMapping(value ="/uploadOrgUser",method = POST)
    @ResponseBody
    public Map<String, Object> uploadOrgUser(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String flag = "failure";
        String msg = "上传成功";
        MultipartHttpServletRequest mtRequest = (MultipartHttpServletRequest) request;//多部分httpRquest对象    是HttpServletRequest类的一个子类接口   支持文件分段上传对象
        MultipartFile upFile = mtRequest.getFile("uploadFile");                       // 直接获取文件对象
        if(null == upFile || upFile.getSize()==0){                                    //文件不存在的情况
            msg = "上传文件不存在或为空文件";
            map.put("flag", flag);
            map.put("msg", msg);
            return map;                                                               //返回错误信息
        }
        String targetPath = request.getServletContext().getRealPath("/file/upload");  //获取服务器 中file/update 的 url地址
        map = this.goodsService.uploadBulkGoods(targetPath,upFile); //调用实现类 返回 界面消息 对象
        return map;
    }
}
