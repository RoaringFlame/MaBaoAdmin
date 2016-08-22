/**
 * Created by lies on 2016/8/22.
 */
$(function () {
    var orderNum;         //订单号
    var consigner;        //收货人
    var goodsStatus;      //订单状态
    var currentPage = 1;
    var pageSize = 7;
    var totalPage = 1;
    var province = $("select[name='province']");  //省下拉框
    var city = $("select[name='city']");  //市下拉框
    var urban = $("select[name='urban']");  //区下拉框

    //获取搜索条件
    function getSearchItem() {
        orderNum = $("#orderNum").val();
        consigner = $("#consigner").val();
        goodsStatus = $("#goodsStatus").val();
        if (goodsStatus == "all") {
            goodsStatus = "";
        }
    }

    //订单状态下拉框初始化
    function initOrderState() {
        $.get("order/orderStatusSelector", {}, function (data) {
            console.log(data);
            $(data).each(function (index, state) {
                $("#goodsStatus").append($("<option></option>")
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
            $.get("order/searchOrder", params, function (data) {
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
                        orderInfo.find("td:eq(3)").text(order.Consignee);
                        orderInfo.find("td:eq(4)").text(order.totalSum);
                        orderInfo.find("td:eq(5)").text(order.state);
                        $("#container").append(orderInfo);
                    });
                } else {
                    totalPage = 1;
                    alert("没有找到符合要求订单！");
                }
            });
        }

    }
    function init() {
        //订单状态下拉框初始化
        initOrderState();
    }

    init();

});
