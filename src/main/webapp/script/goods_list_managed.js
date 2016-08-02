/**
 * Created by maxu on 2016/7/29.
 */
"use strict";
$(function () {
    var searchBtn = $("#goodList").find("button.btn.btn-default");        //获取搜索按钮
    var deleteBtn = $("#operate li:eq(0)");                               //获取删除按钮
    var onSellBtn = $("#operate li:eq(1)");                               //获取上架按钮
    var offSellBtn = $("#operate li:eq(2)");                              //获取下架按钮
    var printBtn = $("#tool li:eq(0)");                                   //获取打印按钮
    var exportExcelBtn = $("#tool li:eq(1)");                             //获取导出商品按钮
    var currentPage = 0;
    var totalPage = 10;
    var pageSize = 7;
    var typeName;                                                        //商品类别
    var goodsPublish;                                                    //商品状态（是否上架）
    var goodsName;                                                       //商品名称
    var goodsId;                                                         //商品货号
    var state;
    var goodsForm = $("#frmGoods");                                  //商品表单
    var goodsTable = $(".table.table-bordered.table-striped");             //商品列表

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
    function initGoodsTypeDropdown() {
        $.get("/goods/", {}, function (data) {
            $(data).each(function (index,type) {
                $("#goodsType").append($("<option></option>")
                    .val(type.key)
                    .text(type.value)
                );
            });
        });
    }

    //商品状态下拉框初始化
    function initGoodsStateDropdown() {
        $.get("/goods/", {}, function (data) {
            $(data).each(function (index,state) {
                $("#goodsType").append($("<option></option>")
                    .val(state.key)
                    .text(state.value)
                );
            });
        });
    }

    //商品列表初始化
    function initGoodsList() {
        //$(".container").empty();
        var params = {
            page: currentPage,
            pageSize: pageSize,
        };
        if (currentPage < totalPage) {
            $.get("/goods/GoodsList", params, function (data) {
                var goodsList = data.items;
                console.log(goodsList);
                $(goodsList).each(function (index, goods) {
                    var goodsInfo = $("#goodsContainer").clone();
                    goodsInfo.show();
                    goodsInfo.find("td:eq(1)").text(goods.id);
                    goodsInfo.find("td:eq(2)").text(goods.upTime);
                    goodsInfo.find("td:eq(3)").text(goods.typeName);
                    goodsInfo.find("td:eq(4)").text(goods.title);
                    goodsInfo.find("td:eq(5)").text(goods.articleNumber);
                    goodsInfo.find("td:eq(6)").text(goods.price);
                    goodsInfo.find("td:eq(7)").text(goods.state);
                    goodsInfo.find("td:eq(8)").text(goods.stockNumber);
                    $(".container").append(goodsInfo);
                });
            });

        }
    }


    //商品详情表单取消按钮
    function cancelForm() {
        goodsForm.find("input").val("");
        goodsForm.find("textarea").val("");
    }

    //新增商品页面提交按钮
    function newGoodsSubmit() {
        goodsForm.attr({action: "", method: "post"});
        goodsForm.submit();
    }

    //修改商品表单初始化
    function initUpdateGoodsForm(goodsId) {
        $.get("goods/getGoods", {goodsId: goodsId}, function (data) {
            console.log(data);
            $("#goodsNameForm").val(data.title);
            $("#goodsPriceForm").val(data.price);
            $("#goodsAddForm").val(data.address);
            $("#goodsDateForm").val(data.upTime);
        });
    }


    //删除商品
    function deleteGoods() {
        var goodsIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var goodsId = $(this).parents("td.check").next("td:eq(0)").text();                              //获取checkbox所在行顺序
            $(this).parents("tr").remove();
            goodsIds += goodsId + ",";
        });
        $.get("/goods/deleteSomeGoods", {ids: goodsIds},function () {

        });

    }

    //商品上架
    function onSell() {
        var goodsIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var goodsId = $(this).parents("td.check").next("td:eq(0)").text();
            $(this).parents("td.check").nextAll("td:eq(6)").text("上架");
            $(this).removeAttr("checked");
            $("#selectAll").removeAttr("checked");
            goodsIds += goodsId + ",";
        });
        $.get("/goods/changeSomeGoods", {ids: goodsIds, state: true}, function () {

        });
    }

    //商品下架
    function offSell() {
        var goodsIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var goodsId = $(this).parents("td.check").next("td:eq(0)").text();
            $(this).parents("td.check").nextAll("td:eq(6)").text("下架");
            $(this).removeAttr("checked");
            $("#selectAll").removeAttr("checked");
            goodsIds += goodsId + ",";
        });
        $.get("/goods/changeSomeGoods", {ids: goodsIds, state: false}, function () {

        });
    }


    //打印按钮

    //导出Excel按钮
    function exportExcel() {
        getSearchItem();
        window.location = "/goodsManager/export/dataGoods?typeName=" + typeName + "&state=" + state + "&title=" + goodsName + "&articleNumber=" + goodsId;

    }

    //表单校验
    function initForm() {

    }

    function init() {
        //商品
        //商品类型下拉框初始化
        initGoodsTypeDropdown();
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

        //点击商品详情表单取消按钮
        goodsForm.find("button.btn.btn-default").click(function () {
            cancelForm();
        });
        //新建商品列表
        $(".btn-toolbar a").click(function () {
            $(".modal-footer button:eq(2)").attr("disable", "true")
                .hide();
            $(".modal-footer button:eq(1)").attr("disable", "false")
                .show();
        });

        //点击商品详情表单提交按钮
        $(".modal-footer button:eq(1)").click(function () {
            alert("11");
        });


        //修改商品按钮
        $("td.edit").click(function () {
            var goodsId = $(this).prevAll("th").text();
            initUpdateGoodsForm(goodsId);
            $(".modal-footer button:eq(1)").attr("disable", "true")
                .hide();
            $(".modal-footer button:eq(2)").attr("disable", "false")
                .show();
        });

        $(".modal-footer button:eq(2)").click(function () {
            alert("22");
        });
        //点击删除按钮
        deleteBtn.click(function () {
            deleteGoods();
        });

        //点击上架按钮
        onSellBtn.click(function () {
            onSell();
        });

        //点击下架按钮
        offSellBtn.click(function () {
            offSell();
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