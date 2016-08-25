"use strict";
$(function(){
    var orderNum;         //订单号
    var consigner;        //收货人
    var goodsStatus;      //订单状态
    var currentPage = 0;
    var pageSize = 7;
    var totalPage=1;

    //时间格式化方法
    function getLocalTime(jsondate) {
        if(jsondate === null || jsondate === "") {
            return "";
        }
        jsondate = "" + jsondate + "";                    //因为jsonDate是number型的indexOf会报错
        if (jsondate.indexOf("+") > 0) {
            jsondate = jsondate.substring(0, jsondate.indexOf("+"));
        }
        else if (jsondate.indexOf("-") > 0) {
            jsondate = jsondate.substring(0, jsondate.indexOf("-"));
        }
        var date = new Date(parseInt(jsondate, 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
    }

    //获取搜索条件
    function getSearchItem() {
        orderNum = $("#orderNum").val();
        consigner = $("#consigner").val();
        goodsStatus = $("#goodsStatus").val();
    }

    //订单状态下拉框初始化
    function getOrderState() {
        $.get("order/orderStatusSelector",{},function(data) {
            $(data).each(function (index, state) {
                $("#orderStatus").append($("<option></option>")
                    .val(state.key)
                    .text(state.value));
            });
        });
    }

    //订单状态


    //发货单列表初始化
    function initOrderList() {
        getSearchItem();
        var params = {
           /* orderId: orderNum,
            state: goodsStatus,*/
            page: currentPage,
            pageSize: pageSize
        };
        if (currentPage <= totalPage) {
            $.get("order/toBeShipped", params, function (data) {
                var orderList=data.items;
                totalPage=data.totalPage;
                console.log(orderList);
                $(orderList).each(function (index, order) {
                    var orderInfo = $("#orderContainer").clone();
                    orderInfo.show();
                    //orderInfo.find("input[type='checkbox']").attr("name", "checkBox");
                    orderInfo.find("td:eq(0) input").attr("name", "checkBox");
                    orderInfo.find("th").text(index + 1);
                    orderInfo.find("td:eq(1)").text(order.id);
                    orderInfo.find("td:eq(2)").text(getLocalTime(order.createTime));
                    orderInfo.find("td:eq(3)").text(order.consignee);
                    orderInfo.find("td:eq(4)").text(getLocalTime(order.portTime));
                    orderInfo.find("td:eq(5)").text(order.state);
                    orderInfo.find("td:eq(6)").text(order.operator);
                    $("#container").append(orderInfo);
                });
            });
        }
    }

    function init(){
        getOrderState();
        initOrderList();
    }

    init();
});
