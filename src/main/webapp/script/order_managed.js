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
                var orderList=data.items;
                totalPage=data.totalPage;
                console.log(orderList);
                $(orderList).each(function (index, order) {
                    var orderInfo = $("#orderContainer").clone();
                    orderInfo.show();
                    orderInfo.find("input[type='checkbox']").attr("name", "checkBox");
                    orderInfo.find("th").text(index + 1);
                    orderInfo.find("td:eq(1)").text(order.id);
                    orderInfo.find("td:eq(2)").text(order.createTime);
                    orderInfo.find("td:eq(3)").text(order.Consignee);
                    orderInfo.find("td:eq(4)").text(order.totalSum);
                    orderInfo.find("td:eq(5)").text(order.state);
                    $("#container").append(orderInfo);

                });
            });
        }
    }

    //根据条件搜索订单
    function searchOrder(){
        getSearchItem();
        initOrderList();
    }

    //发货按钮
    function delivery(){
        var orderIds="";
        $("input[name='checkBox']:checked").each(function () {                       //遍历选中的checkbox
            var orderId = $(this).parents("td").nextAll("td:eq(0)").text();       //获取checkbox所在行的goodsId
            orderIds += orderId + ",";
            alert(orderIds);
        });
        if (orderIds !== "") {
            $.post("/order/changeSomeOrder", {ids: orderIds, state: "ToBeSend"}, function (data) {
                if (data.status == "success") {
                    alert("11");
                    $("#selectAll").removeAttr("checked");
                    $("#container").empty();                                                //清空商品列表
                    initOrderList();                                   //重新加载页面
                } else if (data.status == "failure") {
                    alert("更改订单状态失败!");
                }
            });
        } else {
            alert("您还未选择订单！");
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

        //点击搜索查询商品
        $(".form-group button").click(function(){
            $("#container").empty();
            currentPage=1;
            searchOrder();
        });

        //发货按钮
        $(".delivery").click(function(){
            delivery();
        });
    }

    init();
});
