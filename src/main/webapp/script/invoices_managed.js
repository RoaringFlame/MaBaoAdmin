"use strict";
$(function(){
    var orderNum;         //订单号
    var consigner;        //收货人
    var goodsStatus;      //订单状态
    var flag = 1 ;               //查询标识符
    var currentPage = 0;
    var pageSize = 8;
    var totalPage=1;
    var province = $("select[name='province']");  //省下拉框
    var city = $("select[name='city']");  //市下拉框
    var urban = $("select[name='urban']");  //区下拉框

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
        goodsStatus = $("#orderStatus").val();
    }

    //订单状态下拉框初始化
    function getOrderState() {
        $.get("order/orderStatusSelector",{flag:1},function(data) {
            $(data).each(function (index, state) {
                $("#orderStatus").append($("<option></option>")
                    .val(state.key)
                    .text(state.value));
                $("#orderStatusForm").append($("<option></option>")
                    .val(state.key)
                    .text(state.value)
                );
            });

        });
    }

    //订单状态


    //发货单列表初始化
    function searchOrderList() {
        getSearchItem();
        $("#container").empty();
        var params = {
            flag: flag,
            orderId: orderNum,
            state: goodsStatus,
            page: currentPage,
            pageSize: pageSize
        };
        if (currentPage <= totalPage) {
            $.get("order/searchOrder", params, function (data) {
                var orderList=data.items;
                totalPage=data.totalPage;
                console.log(orderList);
                if (orderList.length > 0) {
                    $(orderList).each(function (index, order) {
                        var orderInfo = $("#orderContainer").clone();
                        orderInfo.show();
                        orderInfo.find("td:eq(0) input").attr("name", "checkBox");
                        orderInfo.find("th").text(index + 1);
                        orderInfo.find("td:eq(1)").text(order.id);
                        orderInfo.find("td:eq(2)").text(getLocalTime(order.createTime));
                        orderInfo.find("td:eq(3)").text(order.consignee);
                        orderInfo.find("td:eq(4)").text(getLocalTime(order.portTime));
                        orderInfo.find("td:eq(5)").text(order.state);
                        orderInfo.find("td:eq(6)").text(order.operator);
                        $("#container").append(orderInfo);
                    })
                }else {
                        totalPage = 1;
                        alert("没有找到符合要求订单！");
                }
            });
        }
    }

    //根据条件搜索订单
    function searchOrder() {
        getSearchItem();
        $("#container").empty();
        flag = 1;
        currentPage = 0;
        searchOrderList();
    }

    //省下拉框初始化
    function provinceSelector() {
        $.get("order/areaSelector", {}, function (data) {
            $(data).each(function (index, provinces) {
                province.append($("<option></option>")            //为下拉框增加节点
                    .val(provinces.key)
                    .text(provinces.value)
                );
            });
            citySelector();
        });
    }

    //市下拉框初始化
    function citySelector() {
        var provinceId = province.val();
        if (provinceId !== "请选择") {
            $.get("order/province/" + provinceId + "/allCity", {}, function (data) {
                city.empty();                                              //清空市下拉框
                city.append($("<option></option>")                         //增加请选择节点
                    .val("请选择")
                    .text("请选择")
                    .attr("disabled", "true")
                );
                $(data).each(function (index, cities) {                                     //为下拉框增加节点
                    city.append($("<option></option>")
                        .val(cities.key)
                        .text(cities.value)
                    );
                });
                countySelector();
            });
        }else {
            city.empty();
            urban.empty();
            city.append($("<option></option>")
                .val("请选择")
                .text("请选择")
            );
            urban.append($("<option></option>")
                .val("请选择")
                .text("请选择")
            );
            city.val("请选择");
            urban.val("请选择");
        }
    }

    //区下拉框初始化
    function countySelector() {
        var cityId = city.val();
        if (cityId !== "请选择") {
            $.get("order/city/" + cityId + "/allCounty", {}, function (data) {
                urban.empty();                                                    //清空区下拉框
                urban.append($("<option></option>")                         //增加请选择节点
                    .val("请选择")
                    .text("请选择")
                    .attr("disabled", "true")
                );
                $(data).each(function (index, urbans) {
                    urban.append($("<option></option>")                          //为区下拉框增加节点
                        .val(urbans.key)
                        .text(urbans.value)
                    );
                });
            });
        }
    }


    //根据条件高级搜索
    function advancedSearch() {
        var orderId = $("#orderNumForm").val();
        var consignee = $("#consignerForm").val();
        var address = $("#addressForm").val();
        var receiver = $("#receiverForm").val();
        var tel = $("#telForm").val();
        var orderStatus = $("#orderStatusForm").val();
        var area = urban.val();
        var transferDate = $("#transferDateForm").val();
        var transferNedDate = $("#transferNedDateForm").val();
        if (area == "请选择") {
            area = "";
        }
        if (orderStatus == "all") {
            orderStatus = "AllState";
        }
        var params = {
            id: orderId,
            buyerName: consignee,
            address: address,
            consignee: receiver,
            phone: tel,
            orderStatus: orderStatus,
            areaId: area,
            startDate: transferDate,
            endDate: transferNedDate
        };
        if (currentPage <= totalPage) {
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: 'order/advancedQueryOrder',
                processData: false,
                dataType: 'json',
                data: JSON.stringify(params),
                success: function (data) {
                    var orderList = data.items;
                    totalPage = data.totalPage;
                    if (orderList.length > 0) {
                        $(orderList).each(function (index, order) {
                            var orderInfo = $("#orderContainer").clone();
                            orderInfo.show();
                            orderInfo.find("input[type='checkbox']").attr("name", "checkBox");
                            orderInfo.find("th").text(index + 1);
                            orderInfo.find("td:eq(1)").text(order.id);
                            orderInfo.find("td:eq(2)").text(getLocalTime(order.createTime));
                            orderInfo.find("td:eq(3)").text(order.consignee);
                            orderInfo.find("td:eq(4)").text(order.totalSum);
                            orderInfo.find("td:eq(5)").text(order.state);
                            $("#container").append(orderInfo);
                        });
                    } else {
                        totalPage = 1;
                        alert("没有找到符合要求订单！");
                    }
                }
            });
        }
    }

    //表单取消按钮
    function cancelForm() {
        $("#searchForm").find("input").val("");
        $("#orderStatusForm").val("all");
        province.val("请选择");
        city.val("请选择");
        urban.val("请选择");
    }

    //删除商品
    function deleteOrder() {
        var orderIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var orderId = $(this).parents("td").next().next().text();       //获取checkbox所在行的goodsId
            orderIds = orderId + ",";                                              //给string类型的goodsIds赋值
        });
        if (orderIds !==  "") {                                                          //如果选中商品
            $.get("order/deleteSomeOrder", {ids: orderIds}, function (data) {       //调用删除商品接口
                if (data.status == "success") {                                       //如果请求成功
                    $("#selectAll").removeAttr("checked");                            //去除全选框的选中状态
                    $("#container").empty();                                          //清空商品列表
                    console.log("=====================");
                    searchOrder();                                                  //重新加载页面
                } else if (data.status == "failure") {                               //如果请求失败弹出警告框
                    alert("删除失败!");
                }
            });
        } else {
            alert("您还未选择商品！");                    //如果没有选中商品弹出警告框
        }
    }

    //初始化分页按钮
    function initPageBtn() {
        //显示当前页数
        $("#page").text(currentPage+1);
        //点击首页
        $("#btn1").click(function () {
            currentPage = 0;                               //点击首页时参数currentPage为0
            $("#container").empty();                       //清空表单数据
            searchOrderList();                               //传参并调用初始化表单方法
            $("#page").text(currentPage+1);
        });
        //点击上一页
        $("#btn2").click(function () {
            if (currentPage == 0) {
                alert("当前已经是首页了!");                 //如果当前页面是首页，点击上一页弹出提示框
            }
            else {
                $("#container").empty();                    //如果不是首页，点击上一页时清空表单
                currentPage--;                              //当前页数减1
                searchOrderList();        //传参并调用初始化表单方法
                $("#page").text(currentPage+1);
            }
        });

        //点击下一页
        $("#btn3").click(function () {
            if (((currentPage+1) == totalPage) || (totalPage == 1)) {                //如果当前页面是最后一页时，点击下一页弹出提示框
                alert("当前已经是最后一页了!");
            }
            else {
                $("#container").empty();                      //如果不是最后一页，点击下一页时清空表单
                currentPage++;                                //当前页数加1
                searchOrderList();          //传参并调用初始化表单方法
                $("#page").text(currentPage+1);
            }
        });

        //点击尾页
        $("#btn4").click(function () {
            currentPage = totalPage-1;                            //点击尾页时参数currentPage为0
            $("#container").empty();                            //清空表单数据
            searchOrderList();                //传参并调用初始化表单方法
            $("#page").text(currentPage+1);
        });

    }
    function init(){
        getOrderState();
        searchOrderList();
        initPageBtn();
        //点击第一行的复选框控制全选和全不选
        $("#selectAll").click(function () {
            $("input[name='checkBox']").prop("checked", $(this).prop("checked"));                 //所有的付款框的状态和第一行复选框状态一致
        });

        //点击搜索查询商品
        $("#searchButton").click(function () {
            $("#container").empty();
            currentPage = 1;
            searchOrder();
        });

        //发货按钮
        $(".delivery").click(function () {
            delivery();
        });

        //高级搜索表单提交按钮
        $("#heightSearch").click(function () {
            $("#container").empty();
            currentPage = 1;
            advancedSearch();
            $("#searchForm").modal('hide');
            cancelForm();
        });

        //点击商品详情表单取消按钮
        $(".modal-footer .btn.btn-default").click(function () {
            cancelForm();                                                 //点击取消按钮清空表单
        });

        //点击表单右上角叉按钮
        $(".modal-header button").click(function () {
            cancelForm();                                                  //点击右上角叉按钮清空表单
        });

        //点击删除按钮btn-toolbar
        $("#deleteOrder").click(function () {
            deleteOrder();             //点击删除按钮删除商品
        });
        console.log("--------------------");
       /* //省下拉框初始化
        provinceSelector();

        //省下拉框改变事件
        province.change(function () {
            citySelector();             //省下拉框改变是调用市下拉框初始化事件
            countySelector();           //省下拉框改变是调用区下拉框初始化事件
        });

        //市下拉框改变事件
        city.change(function () {
            countySelector();           //城市下拉框改变是调用区下拉框初始化事件
        });*/

    }

    init();
});
