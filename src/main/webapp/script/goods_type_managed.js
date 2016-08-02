/**
 * Created by Emma on 2016/7/30.
 * 商品管理商品类别页面
 */
"use strict";
$(function () {

    <!--全局变量-->
    //新建类别表单div的获取
    var createNewForm=$("#createNewForm");
    //当前页面
    var page=0;
    //每页展示的信息数目
    var pageSize=10;
    //总页数
    var totalPage=1;

    //1,初始化新建商品类型
    function initCreateType(){
        //判断点击按钮为新建
        $("#createNew").find("a").click(function(){
                    var info=$("#createNew").find("a").text();
                    if(info=="新建"){
                        $("#exampleModal").find("input,textarea").val("");
                        //新建页面点击取消按钮的控制
                        $("#dismiss-btn").click(function (){
                            //取消事件清除输入的内容
                            $("#exampleModal").find("input,textarea").val("");
                        });
                        //新建页面点击提交按钮的控制
                        //$("#submit-btn").click(function (){
                        //    $.get("url",params,function(data){
                        //        if(createNewForm!=""&&num!=""&&title!=""){
                        //提交按钮跳转到商品类型页面，页面重新加载
                        //            initGoodsTypeList();
                        //        }else{
                        //            alert("请将信息填写完整！");
                        //        }
                        //    })
                        //});
                    }
                });




    }
    //2,初始化删除商品类型
    function initDelType() {
        //选中框事件的实现
        //从页面删除选中内容
    }

    //3，初始化商品类型搜索
    function initSearchKey() {
        //输入关键字，点击搜索按钮实现搜索，页面重新加载为搜索页面
    }

    //4，初始化编辑商品类型
    function initChangeType() {
        //查找点中的编辑项，表单中获取编辑想项对应的商品信息
        //点击取消内容清空
        //点击提交数据提交，页面信息刷新
    }

    //5,类型列表初始化
    function initGoodsTypeList(){
        //获取页面中需要的数据信息并展示
    }

    //6,分页功能的实现
    function initPage(){

    }

    //7,页面初始化
    function initGoodsTypePage(){
        //1,初始化新建商品类型
        initCreateType();
        //2,初始化删除商品类型
        initDelType();
        //3，初始化商品类型搜索
        initSearchKey();
        //4，初始化编辑商品类型
        initChangeType();
        //5,类型列表初始化
        initGoodsTypeList();
        //6,分页功能的实现
        initPage();
    }

    //8，初始化函数
    function init() {
        //6,页面初始化
        initGoodsTypePage();
    }

    //8,调用初始化函数
    init();


    <!--全局变量-->
    //新建类别表单div的获取
    //var createNewForm=$("#createNewForm");
    //var page=0;//当前页面
    //var pageSize=0;//页面数
    //var totalPage=1;//总页数
    //
    //<!--初始化新建相关-->
    //function initCreateNew(){
    //
    //    //分类名称的获取
    //    var typeName=createNewForm.find("input[name='assortmentForm']").val();
    //    //上级分类的获取
    //    //var typeId=createNewForm.find("input[name='categoryParent']").val();
    //    //数量单位的获取
    //    var num=createNewForm.find("input[name='assortmentNum']").val();
    //    //分类描述的获取
    //    var title=createNewForm.find("input[name='assortmentDetail']").val();
    //    //新建表单的初始化
    //
    //    //var params={
    //    //    typeName:typeName,
    //    //    num:num,
    //    //    title:title
    //    //}
    //    $("#createNew").find("a").click(function(){
    //        var info=$("#createNew").find("a").text();
    //        if(info=="新建"){
    //            $("#exampleModal").find("input,textarea").val("");
    //            //新建页面点击取消按钮的控制
    //            $("#dismiss-btn").click(function (){
    //                $("#exampleModal").find("input,textarea").val("");
    //            });
    //            //新建页面点击提交按钮的控制
    //            //$("#submit-btn").click(function (){
    //            //    $.get("url",params,function(data){
    //            //        if(createNewForm!=""&&num!=""&&title!=""){
    //            //            initGoodsTypeList();
    //            //        }else{
    //            //            alert("请将信息填写完整！");
    //            //        }
    //            //    })
    //            //});
    //        }
    //    });
    //
    //}
    //
    ////修改相关
    //function initChangeMsg(){
    //    //获取到选中项的值并展示，取消时值不变原样返回，点击保存就提示是否修改，修改完成后显示
    //    //如果没有选中项提示没有选中不能修改
    //    //如果选中多项提示只能单项修改
    //    $($(".goods-del").text()).click(function () {
    //        console.log("hello");
    //    })
    //}
    //
    ////删除相关
    //function  initDelete(){
    //    //如果没有选中项提示没有选中不能删除
    //    $("#delBtn").click(function () {
    //        //获取选中项
    //        var checkedItems=$("input[name='allCheck']:checked");
    //        var goodsIds = "";  //存储选中项的id值
    //        if(checkedItems.val()){
    //            $(checkedItems).each(function () {                     //遍历选中的checkbox
    //                var goodsId = $(this).parents("tr").find("td:eq(1)").text();           //获取checkbox所在行顺序
    //                goodsIds+=goodsId + ",";
    //                console.log(goodsIds);
    //                $(this).parents("tr").remove();
    //            });
    //        }else{
    //            alert("请至少选择一项才能进行删除操作！");
    //        }
    //    })
    //
    //}
    //
    //
    ////商品类别名搜索
    //function initSearchByType(){
    //    $("#searchByType").click(function () {
    //        initGoodsTypeList();
    //    });
    //}
    //
    ////选中所有的功能
    //function initCheckBoxAll(){
    //    $("#textSearch").click(function () {
    //        $("input[name='allCheck']").prop("checked",$(this).prop("checked"));
    //    });
    //}
    //
    //
    ////商品类别展示初始化
    //function initGoodsTypeList(){
    //    //传参
    //    var searchKey=$("#textSearch").val();//搜索关键字的传入
    //    if(page<=totalPage){
    //        $.get("url","", function (data) {
    //            console.log(data);
    //            //商品编号展示
    //            $("#textSearch").find("tr:eq(1)>th:eq(1)").text("编号数值展示");
    //            //商品类别展示
    //            $("#textSearch").find("tr:eq(1)>th:eq(2)").text("商品类别展示");
    //            //商品数量展示
    //            $("#textSearch").find("tr:eq(1)>th:eq(3)").text("商品数量展示");
    //            //数量单位展示
    //            $("#textSearch").find("tr:eq(1)>th:eq(4)").text("数量单位展示");
    //        })
    //    }
    //
    //}
    //
    //
    //
    //
    ////页面初始化
    //function initTypePage(){
    //    $.get("url","", function (data) {
    //        //新建商品的表单
    //        initCreateNew();
    //        //商品类别展示初始化
    //        initGoodsTypeList();
    //        //商品类别名搜索
    //        initSearchByType();
    //    });
    //    //选中所有和取消
    //    initSearchByType();
    //}
    //
    ////初始化函数
    //function init(){
    //    //页面初始化
    //    initTypePage();
    //    //选中所有和取消
    //    initCheckBoxAll();
    //    //初始化新建表單
    //    initCreateNew();
    //    ////按钮点击表单事件
    //    //initChangeBtn();
    //    //删除按钮
    //    initDelete();
    //    initChangeMsg();
    //}
    //
    ////调用初始化函数
    //init();
});
