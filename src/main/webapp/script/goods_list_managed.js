/**
 * Created by maxu on 2016/7/29.
 */
"use strict";
$(function () {
    var searchBtn = $("#goodList").find("button.btn.btn-default");        //获取搜索按钮
    var deleteBtn = $("#operate li:eq(1)");                               //获取删除按钮
    var onSellBtn = $("#operate li:eq(2)");                               //获取上架按钮
    var offSellBtn = $("#operate li:eq(3)");                              //获取下架按钮
    var printBtn = $("#tool li:eq(0)");                                   //获取打印按钮
    var exportExcelBtn = $("#tool li:eq(1)");                             //获取导出商品按钮
    var currentPage = 0;
    var totalPage;
    var pageSize = 7;
    var backGoods;
    var typeName;                                                        //商品类别
    var goodsPublish;                                                    //商品状态（是否上架）
    var goodsName;                                                       //商品名称
    var goodsId;                                                         //商品货号
    var state;
    var newGoodsForm = $("#newGoodsModal");                                //新增商品表单
    var updateGoodsForm = $("#updateGoodsModal");                          //修改商品表单

    //获取搜索条件
    function getSearchItem() {
        typeName = $("#goodsType option:selected").text();                      //获取选中的商品类别
        goodsPublish = $("#goodsPublish option:selected").text();               //获取选中的商品状态（是否上架）
        goodsName = $("#goodsName").val();                                      //获取商品名称
        goodsId = $("#goodsId").val();                                          //获取商品货号
        if (typeName == "所有类别") {
            typeName = "";                                                      //如果类型是所有类别商品类别的值设为空
        }
        if (goodsPublish == "上架") {
            state = true;
        } else if (goodsPublish == "下架") {
            state = false;
        } else {
            state = "";
        }
    }

    //商品类别下拉框初始化
    function initTypeDropDown() {
        $.get("", {}, function (data) {
            $(data).each(function (index, goodsType) {
                $("#goodsType").append($("<option></option>")
                    .val(goodsType.key)
                    .text(goodsType.value)
                );
            });
        });
    }

    //商品状态下拉框初始化
    function initStateDropDown() {
        $.get("", {}, function (data) {
            $(data).click(function (index, goodsState) {
                $("#goodsPublish").append($("<option></option>")
                    .val(goodsState.key)
                    .text(goodsState.value)
                );
            });
        })

    }

    //商品列表初始化
    function initGoodsList() {
        getSearchItem();
        var params = {
            page: currentPage,
            pageSize: pageSize,
            typeName: typeName,
            state: state,
            title: goodsName,
            articleNumber: goodsId
        };
        if (currentPage < totalPage) {
            $.get("", params, function (data) {
                var goodsList = data.items;
                totalPage = data.totalPage;
                $(goodsList).each(function (index, goods) {
                    var goods = backGoods.clone();
                    goods.find("th").text(goods.id);
                    goods.find("td:eq(0)").text(goods.upTime);
                    goods.find("td:eq(1)").text(goods.typeName);
                    goods.find("td:eq(2)").text(goods.title);
                    goods.find("td:eq(3)").text(goods.articleNumber);
                    goods.find("td:eq(4)").text(goods.price);
                    goods.find("td:eq(5)").text(goods.state == 1 ? "上架" : "下架");
                    goods.find("td:eq(6)").text(goods.stockNumber);
                    $("#newGoodsContainer").append(goods);
                });
            });

        }
    }

    //根据条件搜索商品
    function searchGoods() {
        $("#newGoodsContainer").empty();
        currentPage = 0;
        initGoodsList();
    }

    //新建商品按钮

    //新建商品页面取消按钮
    newGoodsForm.find("button.btn.btn-default").click(function () {
        newGoodsForm.find("input").val("");
        newGoodsForm.find("textarea").val("");
    });
    //新建商品页面提交按钮

    //修改商品按钮

    //修改商品页面取消按钮
    updateGoodsForm.find("button.btn.btn-default").click(function () {
        updateGoodsForm.find("input").val("");
        updateGoodsForm.find("textarea").val("");
    });
    //修改商品页面提交按钮


    //删除商品按钮

    //商品上架按钮

    //商品下架按钮

    //修改商品按钮

    //打印按钮

    //导出Excel按钮
    function exportExcel() {
        getSearchItem();
        window.location = "/goodsManager/export/dataGoods?typeName=" + typeName + "&state=" + state + "&title=" + goodsName + "&articleNumber=" + goodsId;

    }

    //表单校验
    function initForm(){
       var goodsName=$("#newGoodsName").val();
        var goodsPrice=$().val();
    }

    function init() {
        //商品类别下拉框初始化
        initTypeDropDown();
        //商品状态下拉框初始化
        initStateDropDown();
        //商品列表初始化
        initGoodsList();
        //点击搜索按钮事件
        searchBtn.click(function () {
            searchGoods();
        });
        //点击第一行的复选框控制全选和全不选
        $("#selectAll").click(function () {
            $("input[name='checkBox']").prop("checked", $(this).prop("checked"));
        });
        //点击添加商品表单取消按钮

        //点击添加商品表单提交按钮

        //点击修改商品表单取消按钮


        //点击修改商品表单提交按钮

        //点击删除按钮
        deleteBtn.click(function () {
            alert("删除");
        });
        //点击上架按钮
        onSellBtn.click(function () {
            alert("上架");
        });
        //点击下架按钮
        offSellBtn.click(function () {
            alert("下架");
        });
        //点击打印按钮
        printBtn.click(function () {
            alert("打印");
        });
        //点击导出按钮事件
        exportExcelBtn.click(function () {
            exportExcel();
        });


    }

    init();
});