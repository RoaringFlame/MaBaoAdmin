"use strict";
$(function () {
    var orderNum;         //订单号
    var consigner;        //收货人
    var goodsStatus;      //订单状态
    var currentPage = 1;
    var pageSize = 7;
    var totalPage=1;

    //获取搜索条件
    function getSearchItem() {
        orderNum = $("#orderNum").val();
        consigner = $("#consigner").val();
        goodsStatus = $("#goodsStatus").val();
    }

    //订单状态下拉框初始化
    function initOrderState() {
        $.get("/order", {}, function (data) {
            console.log(data);
            $(data).each(function (index, state) {
                $(".goodsStatus").append($("<option></option>")
                    .val(state.key)
                    .text(state.value)
                );
            });
        });
    }

    //订单列表初始化
    function initOrderList() {
        getSearchItem();
        var params = {
            orderId: orderNum,
            state: goodsStatus,
            page: currentPage,
            pageSize: pageSize,
        };
        if (currentPage <= totalPage) {
            $.get("/order/searchOrder", params, function (data) {
                var order=data.items;
                totalPage=data.totalPage;
                console.log(order);
                $(order).each(function (index, order) {
                    var orderList = $("#orderContainer").clone();
                    orderList.find("th:eq(0)").text(index + 1);
                    orderList.find("th:eq(1)").text(order.id);
                    orderList.find("th:eq(2)").text(order.createTime);
                    orderList.find("th:eq(3)").text(order.Consignee);
                    orderList.find("th:eq(4)").text(order.totalSum);
                    orderList.find("th:eq(5)").text(order.state);
                    $("#container").append(orderList);
                });
            });
        }
    }

    //发货按钮
    function delivery(){
        var goodsIds="";
        $("input[name='checkBox']:checked").each(function () {                       //遍历选中的checkbox
            var goodsId = $(this).parents("td.check").next("td:eq(0)").text();       //获取checkbox所在行的goodsId
            goodsIds += goodsId + ",";
        });
        if (goodsIds !== "") {
            $.get("/goods/changeSomeGoods", {ids: goodsIds, state: true}, function (data) {
                if (data.status == "success") {
                    $("#selectAll").removeAttr("checked");
                    $(".container").empty();                                                //清空商品列表
                    initOrderList(currentPage, pageSize);                                   //重新加载页面
                } else if (data.status == "failure") {
                    alert("更改商品状态失败!");
                }
            });
        } else {
            alert("您还未选择商品！");
        }
    }

    function init() {
        //订单状态下拉框初始化
        initOrderState();
        //订单列表初始化
        initOrderList();

        //点击第一行的复选框控制全选和全不选
        $("#selectAll").click(function () {
            $("input[name='checkBox']").prop("checked", $(this).prop("checked"));                 //所有的付款框的状态和第一行复选框状态一致
        });

        //发货按钮
        $(".delivery").click(function(){
            delivery();
        });
    }

    init();
});
