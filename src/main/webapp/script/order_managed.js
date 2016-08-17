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

    //时间格式化方法
    function getLocalTime(jsondate) {
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
                    orderInfo.find("td:eq(2)").text(getLocalTime(order.createTime));
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
        });
        if (orderIds !== "") {
            $.post("/order/changeSomeOrder", {ids: orderIds, orderStatus: "ToBeReceipt"}, function (data) {
                if (data.status == "success") {                        //如果请求成功
                    $("#selectAll").removeAttr("checked");             //去除全选框的选中状态
                    $("#container").empty();                           //清空商品列表
                    initOrderList();                                   //重新加载页面
                } else if (data.status == "failure") {                //如果请求失败弹出警告框
                    alert("更改订单状态失败!");
                }
            });
        } else {                                                      //如果没有选中商品弹出警告框
            alert("您还未选择订单！");
        }
    }

    //初始化分页按钮
    function initPageBtn() {
        //显示当前页数
        $("#page").text(currentPage);
        //点击首页
        $("#btn1").click(function () {
            currentPage = 1;                               //点击首页时参数currentPage为0
            $(".container").empty();                       //清空表单数据
            initOrderList();                               //传参并调用初始化表单方法
            $("#page").text(currentPage);
        });

        //点击上一页
        $("#btn2").click(function () {
            if (currentPage == 1) {
                alert("当前已经是首页了!");                 //如果当前页面是首页，点击上一页弹出提示框
            }
            else {
                $(".container").empty();                    //如果不是首页，点击上一页时清空表单
                currentPage--;                              //当前页数减1
                initOrderList();        //传参并调用初始化表单方法
                $("#page").text(currentPage);
            }
        });

        //点击下一页
        $("#btn3").click(function () {
            if ((currentPage == totalPage) || (totalPage == 1)) {                //如果当前页面是最后一页时，点击下一页弹出提示框
                alert("当前已经是最后一页了!");
            }
            else {
                $(".container").empty();                      //如果不是最后一页，点击下一页时清空表单
                currentPage++;                                //当前页数加1
                initOrderList();          //传参并调用初始化表单方法
                $("#page").text(currentPage);
            }
        });

        //点击尾页
        $("#btn4").click(function () {
            currentPage = totalPage;                            //点击尾页时参数currentPage为0
            $(".container").empty();                            //清空表单数据
            initOrderList();                //传参并调用初始化表单方法
            $("#page").text(currentPage);
        });

    }

    function init() {
        //订单状态下拉框初始化
        initOrderState();
        //订单列表初始化
        initOrderList();
        //初始化分页按钮
        initPageBtn();

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
