"use strict";
$(function(){
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

    //订单状态


    //发货单列表初始化
    function initOrderList() {
        getSearchItem();
        var params = {
            orderId: orderNum,
            state: goodsStatus,
            page: currentPage,
            pageSize: pageSize,
        };
        if (currentPage <= totalPage) {
            $.get("/order/toBeShipped", params, function (data) {
                var orderList=data.items;
                totalPage=data.totalPage;
                console.log(orderList);
                $(orderList).each(function (index, order) {
                    var orderInfo = $("#orderContainer").clone();
                    orderInfo.show();
                    orderInfo.find("input[type='checkbox']").attr("name", "checkBox");
                    orderInfo.find("th").text(index + 1);
                    orderInfo.find("td:eq(1)").text(order.id);
                    orderInfo.find("td:eq(2)").text(getLocalTime(order.createTime));
                    orderInfo.find("td:eq(3)").text(order.Consignee);
                    orderInfo.find("td:eq(4)").text(order.totalSum);
                    orderInfo.find("td:eq(5)").text(order.Consignee);
                    $("#container").append(orderInfo);

                });
            });
        }
    }

    function init(){
        initOrderList();
    }

    init();
});
