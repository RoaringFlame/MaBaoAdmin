package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.GoodsInVO;
import com.mabao.admin.controller.vo.GoodsSearchVO;
import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsBrand;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.repository.BaseDao;
import com.mabao.admin.repository.GoodsRepository;
import com.mabao.admin.service.GoodsBrandService;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.ExcelUtil;
import com.mabao.admin.util.ExportUtil;
import com.mabao.admin.util.PageVO;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsBrandService goodsBrandService;
    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private BaseDao baseDao;
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");       //得到系统属性的文件分隔符

    /**
     * 查询商品信息
     * @param goodsId           商品ID
     * @return
     */
    @Override
    public Goods get(Long goodsId) {
        return this.goodsRepository.findOne(goodsId);
    }

    /**
     * 修改商品
     * @param goodsInVO        商品对象，需包含用户ID
     * @return                  保存的商品对象
     */
    @Override
    public Goods saveGoods(GoodsInVO goodsInVO) {
        Goods goods = this.goodsRepository.findOne(goodsInVO.getId());
        goods.setTitle(goodsInVO.getTitle());                                   //商品名称
        goods.setUser(this.userService.get(goodsInVO.getUser_id()));
        goods.setNewDegree(goodsInVO.getNewDegree());                           //新旧级别
        goods.setOldPrice(goodsInVO.getOldPrice());
        goods.setPrice(goodsInVO.getPrice());                                   //价格
        GoodsType goodsType = this.goodsTypeService.findOne(goodsInVO.getTypeId());
        goods.setType(goodsType);
        goods.setTypeName(goodsType.getTypeName());
        GoodsBrand goodsBrand = this.goodsBrandService.get(goodsInVO.getBrandId());
        goods.setBrand(goodsBrand);
        goods.setBrandName(goodsBrand.getBrandName());
        goods.setUpTime(goodsInVO.getUpTime());                                            //上传时间呗设定为购买时间
        goods.setStockNumber(goodsInVO.getStockNumber());
        goods.setMessage(goodsInVO.getMessage());                               //商品介绍
        goods.setBabyType(goodsInVO.getBabyType());//设置适合宝宝为男*/
        if(goodsInVO.getStockNumber() == 0) {
            goods.setSellEnd(true);
        }   else {
            goods.setSellEnd(false);
        }
        return this.goodsRepository.saveAndFlush(goods);
    }

    /**
     * 新建商品
     * @param goodsInVO         商品对象，需包含用户ID
     * @return                  保存的商品对象
     */
    @Override
    public Goods newGoods(GoodsInVO goodsInVO) {
        Goods goods = new Goods();
        goods.setTitle(goodsInVO.getTitle());                                   //商品名称
        goods.setUser(this.userService.get(goodsInVO.getUser_id()));
        goods.setNewDegree(goodsInVO.getNewDegree());                           //新旧级别
        goods.setOldPrice(goodsInVO.getOldPrice());
        goods.setPrice(goodsInVO.getPrice());                                   //价格
        GoodsType goodsType = this.goodsTypeService.findOne(goodsInVO.getTypeId());
        goods.setType(goodsType);
        goods.setTypeName(goodsType.getTypeName());
        GoodsBrand goodsBrand = this.goodsBrandService.get(goodsInVO.getBrandId());
        goods.setBrand(goodsBrand);
        goods.setBrandName(goodsBrand.getBrandName());
        goods.setUpTime(goodsInVO.getUpTime());                                  //上传时间呗设定为购买时间
        goods.setStockNumber(goodsInVO.getStockNumber());
        goods.setMessage(goodsInVO.getMessage());                                 //商品介绍
        goods.setBabyType(goodsInVO.getBabyType());                               //设置适合宝宝为男
        if(goodsInVO.getStockNumber() == 0) {
            goods.setSellEnd(true);
        }   else {
            goods.setSellEnd(false);
        }
        goods.setState(false);                                                    //设置状态为true
        return this.goodsRepository.save(goods);
    }

    /**
     * 获取所有商品信息
     * @param page                  页数
     * @param pageSize              每页大小
     * @return
     */
    @Override
    public Page<Goods> getAllGoods(int page, int pageSize) {
        return this.goodsRepository.findAll(new PageRequest(page, pageSize));
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
     *
     * @param goodsSearchVO                 搜索商品VO
     * @param page                          页数
     * @param pageSize                      每页大小
     * @return
     */
    @Override
    public PageVO<GoodsVO> selectGoods(GoodsSearchVO goodsSearchVO, int page, int pageSize) {
        String JPQL = "select g from Goods g "+
                "inner join fetch g.type gt " ;
        String JPQLCount = "select count(g) from Goods g ";
        StringBuilder str = new StringBuilder("where 1 = 1 ");
        List<Object> args = new ArrayList<>();

        if (goodsSearchVO.getGoodsTypeId() !=null && !"".equals(goodsSearchVO.getGoodsTypeId()) ) {
            args.add(goodsSearchVO.getGoodsTypeId());
            str.append(" and g.type.id = ?");
            str.append(args.size());
        }
        if (goodsSearchVO.getState() != null && !"".equals(goodsSearchVO.getState())) {
            args.add(goodsSearchVO.getState());
            str.append(" and g.state = ?");
            str.append(args.size());
        }
        if (goodsSearchVO.getTitle() != null && !"".equals(goodsSearchVO.getTitle())) {
            args.add("%"+goodsSearchVO.getTitle()+"%");
            str.append(" and g.title like ?");
            str.append(args.size());
        }
        if (goodsSearchVO.getArticleNumber() != null && !"".equals(goodsSearchVO.getArticleNumber())) {
            args.add("%"+goodsSearchVO.getArticleNumber()+"%");
            str.append(" and g.articleNumber like ?");
            str.append(args.size());
        }

        String jpqlAll = JPQL + str.toString();
        String count = JPQLCount + str.toString();
        try{
            //分页时从第0页算起
            PageVO<Goods> pages = this.baseDao.findAll(jpqlAll,count,args.toArray(),page-1,pageSize);
            PageVO<GoodsVO> pageVO = new PageVO<>();
            pageVO.setItems(GoodsVO.generateBy(pages.getItems()));
            pageVO.setCurrentPage(pages.getCurrentPage()+1);
            pageVO.setTotalRow(pages.getTotalRow());
            pageVO.setPageSize(pages.getPageSize());
            return pageVO;
        } catch (Exception e) {
            return null;
        }
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

    /**
     * 商品筛选批量导出
     *
     * @param goodsSearchVO                   商品搜索VO
     * @param request
     * @param response
     */
    @Override
    public void exportDataGoodsDetail(GoodsSearchVO goodsSearchVO,HttpServletRequest request, HttpServletResponse response) {
        //设置路径
        String docsPath = request.getSession().getServletContext()
                .getRealPath("");                                                   //模板文件路径
        docsPath = docsPath.substring(0, docsPath.indexOf("classes"));              //获得类似“temp.test.'”
        docsPath = docsPath + "src\\main\\webapp\\uploadFile";
        String fileName = "商品数据明细表" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls";           //导出Excel文件名
        String filePath = docsPath + FILE_SEPARATOR + fileName;
        //数据填充
        ExcelUtil<GoodsVO> ex = new ExcelUtil<>();
        String[] headers = {"编号", "时间", "商品类别", "商品名称", "货号", "价格", "上架", "库存"};
        String[] columns = {"id", "upTime", "typeName", "title", "articleNumber", "price", "state", "stockNumber"};
        //JPQL拼接
        String JPQL = "select g from Goods g "+
                "inner join fetch g.type gt " ;
        StringBuilder str = new StringBuilder("where 1 = 1 ");
        List<Object> args = new ArrayList<>();
        if (goodsSearchVO.getGoodsTypeId() !=null && !"".equals(goodsSearchVO.getGoodsTypeId())) {
            args.add(goodsSearchVO.getGoodsTypeId());
            str.append(" and g.type.id = ?");
            str.append(args.size());
        }
        if (goodsSearchVO.getState() != null && !"".equals(goodsSearchVO.getState())) {
            args.add(goodsSearchVO.getState());
            str.append(" and g.state = ?");
            str.append(args.size());
        }
        if (goodsSearchVO.getTitle() != null && !"".equals(goodsSearchVO.getTitle())) {
            args.add("%"+goodsSearchVO.getTitle()+"%");
            str.append(" and g.title like ?");
            str.append(args.size());
        }
        if (goodsSearchVO.getArticleNumber() != null && !"".equals(goodsSearchVO.getArticleNumber())) {
            args.add("%"+goodsSearchVO.getArticleNumber()+"%");
            str.append(" and g.articleNumber like ?");
            str.append(args.size());
        }
        String jpqlAll = JPQL + str.toString();
        List<Goods> data = this.baseDao.findAll(jpqlAll,args.toArray());
        List<GoodsVO> dataSet = GoodsVO.generateBy(data);
        try {
            OutputStream out = new FileOutputStream(filePath);
            ex.exportExcel("商品数据明细表", headers, columns, dataSet, out, "yy-MM-dd HH:mm:ss");
            ExcelUtil.download(filePath, response);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 商品批量导入
     * @param targetPath
     * @param upFile
     * @return
     */
    @Override
    public Map<String, Object> uploadBulkGoods(String targetPath, MultipartFile upFile) {
        Map<String,Object> rm = new HashMap<String,Object>();
         String flag ="failure";
         String msg = "上传失败";
         File f = new File(targetPath); //实例硬盘中文件夹（路径）对象
        if(!f.exists()){//判断此路径/文件夹是否存在
            f.mkdirs(); //如果不存在  则创建文件夹/目录
        }
         String originalName = upFile.getOriginalFilename();//获取文件对象原始文件名
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
         String tag = sdf.format(new Date());
         String upFileName = targetPath + File.separator+tag+"_"+originalName;// 拼接出文件的要存储的位置（全路径）
         File file = new File(upFileName);//创建 内存中的File对象
         if(file.exists()){         //判断是否存在
                file.delete();      //如果有重名文件存在  就删除文件
                                    // 这个对象对应的硬盘必须删  不能存在  如果已经存在 则会抛出
                                    // IOException异常
             }

        try {
            upFile.transferTo(file);//转存文件  写入硬盘  //这个  本质还是一样的打开流传文件  需要注意 file对应的硬盘中的文件不能存在 需要删除  否则会抛出 文件已经存在且不能删除 异常
            // 校验上传数据
            /**  辅助方法一 **/
//            Map<String, Object> validData = validUpload(file);
        } catch (IOException e){

        }
         return null;
    }

}
