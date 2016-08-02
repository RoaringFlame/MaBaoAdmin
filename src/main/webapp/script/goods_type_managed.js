/**
 * Created by Emma on 2016/7/30.
 * 商品管理商品类别页面
 */
"use strict";
$(function () {

    <!--全局变量-->
    //新建类别表单div的获取
    var createNewForm = $("#createNewForm");
    //当前页面
    var page = 0;
    //每页展示的信息数目
    var pageSize = 7;
    //总页数
    var totalPage = 1;
    //列表格式隐藏
    var hideGoods = $("#hideGoods").find("tr");
    //商品类型列表
    var tableListForm = $("#tableListForm").find("tbody");
    //商品类型Id
    var id;//一级分类Id
    var type;//一级分类名称
    var unitOfQuantity;//数量单位
    var goodsTypeIntroduction;          //商品类型描述
    var goodsNumber;//商品数量
    var checkedItems;
    var goodsIds = "";  //存储选中项的id值

    //1,初始化新建商品类型
    function initCreateType() {
        var params = {
            id: id,
            typeName: type,
            unitOfQuantity: unitOfQuantity,
            goodsTypeIntroduction: goodsTypeIntroduction,
            goodsNumber: goodsNumber
        };

        //判断点击按钮为新建
        $("#createNew").find("a").click(function () {
            //分类名称的获取
            var typeNameList = createNewForm.find("input[name='typeName']").val();
            //数量单位的获取
            var num = createNewForm.find("input[name='goodsTypeIntroduction']").val();
            //分类描述的获取
            var title = createNewForm.find("textarea[name='assortmentDetail']").val();
            var info = $("#createNew").find("a").text();
            if (info == "新建") {
                //新建页面点击取消按钮的控制
                $("#dismiss-btn").click(function () {
                    //取消事件清除输入的内容
                    $("#exampleModal").find("input,textarea").val("");
                });
                //新建页面点击提交按钮的控制
                $("#submit-btn").click(function () {
                    $.get("/goodsType/addGoodsType", params, function (data) {
                        if (typeNameList != "" && num != "" && title != "") {
                            //提交按钮跳转到商品类型页面，页面重新加载
                        } else {
                            alert("请将信息填写完整！");
                        }
                    });
                    alert("请将信息填写完整！");
                });
            }
        });
    }

    //选中所有的实现
    function initCheckBoxAll() {
        $("#textSearch").click(function () {
            $("input[name='allCheck']").prop("checked", $(this).prop("checked"));
        });
    }

    //2,初始化删除商品类型
    function initDelType() {
        //删除按钮的点击事件
        $("#delBtn").click(function () {
            //获取选中项
            checkedItems = $("input[name='allCheck']:checked");
            if (checkedItems.val()) {
                $(checkedItems).each(function () {                     //遍历选中的checkbox
                    var goodsId = $(this).parents("tr").find("td:eq(1)").text();           //获取checkbox所在行顺序
                    goodsIds += goodsId + ",";
                    console.log(goodsIds);
                    $.get("/goodsType/deleteSomeGoodsType", {ids: goodsIds}, function (data) {
                        $(this).parents("tr").remove();//从页面删除选中内容
                    });
                });
                window.location.reload();
            } else {
                alert("请至少选择一项才能进行删除操作！");//如果没有选中项提示没有选中不能删除
            }
        });
    }

    //3，初始化商品类型搜索
    function initSearchKey() {
        var params = {
            goodsTypeName: $("#goodsType").val(),
            page: page,
            pageSize: pageSize
        };
        //输入关键字，点击搜索按钮实现搜索，页面重新加载为搜索页面
        $("#searchByType").click(function () {
            console.log("搜索开始");
            console.log($("#goodsType").val());
            console.log(params);
            $.get("/goodsType/", params, function (data) {
                console.log(data);
                initGoodsTypeList(data);
            })
        });
    }

    //4，初始化编辑商品类型
    function initChangeType() {
        //查找点中的编辑项，表单中获取编辑想项对应的商品信息
        //点击取消内容清空
        //点击提交数据提交，页面信息刷新
    }

    //5,类型列表初始化
    function initGoodsTypeList(goodsTypeList) {
        //获取页面中需要的数据信息并展示
        $(goodsTypeList.items).each(function (index, goodsType) {
            console.log(index, goodsType);
            var typeList = hideGoods.clone();
            //编号数值展示
            typeList.find("td:eq(1)").text(goodsType.id);
            //商品类别展示
            typeList.find("td:eq(2)").text(goodsType.typeName);
            //商品数量展示
            typeList.find("td:eq(3)").text(goodsType.unitOfQuantity);
            //数量单位展示
            typeList.find("td:eq(4)").text(goodsType.goodsNumber);
            tableListForm.append(typeList);
        });
    }

    //6,分页功能的实现
    function initPage(pageMsg) {
        var totalPage = pageMsg.totalPage;
        if (page >= 0 && page <= totalPage - 1) {
            console.log(pageMsg);
            $("#btn1").click(function () {
                if (page != 0) {
                    var pages = {
                        page: 0,
                        pageSize: pageSize
                    };
                    $.get("/goodsType", pages, function (data) {
                        console.log(btn1);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList(data);
                    });
                }
            });
            $("#btn2").click(function () {
                console.log(btn2);
                if (page > 0) {
                    page = page - 1;
                    console.log(1);
                    var pages = {
                        page: page,
                        pageSize: pageSize
                    };
                    console.log(2);
                    $.get("/goodsType", pages, function (data) {
                        console.log(3);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList(data);
                    });
                }
            });
            $("#btn3").click(function () {
                if (page < totalPage - 1) {
                    page = page + 1;
                    var pages = {
                        page: page,
                        pageSize: pageSize
                    };
                    $.get("/goodsType", pages, function (data) {
                        console.log(btn3);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList(data);
                    });
                }
            });
            $("#btn4").click(function () {
                if (page != totalPage - 1) {
                    var pages = {
                        page: totalPage - 1,
                        pageSize: pageSize
                    };
                    $.get("/goodsType", pages, function (data) {
                        console.log(btn4);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList(data);
                    });
                }

            });
        }
    }


    //7,页面初始化
    function initGoodsTypePage() {
        //传入参数
        var params = {
            page: page,
            pageSize: pageSize
        };
        $.get("/goodsType", params, function (data) {
            //打印信息
            console.log(data);
            //1,初始化新建商品类型
            initCreateType();
            //选中所有
            initCheckBoxAll();
            //2,初始化删除商品类型
            initDelType();
            //3，初始化商品类型搜索
            initSearchKey();
            //4，初始化编辑商品类型
            initChangeType();
            //5,商品类型列表初始化
            initGoodsTypeList(data);
            //6,分页功能的实现
            initPage(data);
        });
    }

    //8，初始化函数
    function init() {
        //7,页面初始化
        initGoodsTypePage();
    }

    //8,调用初始化函数
    init();


    <!--全局变量-->
    //<!--初始化新建相关-->
    //function initCreateNew(){
    //
    //
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
});
