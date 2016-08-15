/**
 * Created by maxu on 2016/7/29.
 */
"use strict";
$(function () {
    var searchBtn = $("#search");        //获取搜索按钮
    var deleteBtn = $("#operate li:eq(0)");                               //获取删除按钮
    var onSellBtn = $("#operate li:eq(1)");                               //获取上架按钮
    var offSellBtn = $("#operate li:eq(2)");                              //获取下架按钮
    var printBtn = $("#tool li:eq(0)");                                   //获取打印按钮
    var exportExcelBtn = $("#tool li:eq(1)");                             //获取导出商品按钮
    var currentPage = 1;                                                  //当前页数
    var totalPage = 1;                                                    //总页数
    var pageSize = 7;                                                     //页面记录个数
    var goodsTypeId;                                                         //商品类别
    var goodsPublish;                                                     //商品状态（是否上架）
    var goodsName;                                                        //商品名称
    var goodsNum;                                                          //商品货号
    var state;                                                            //商品状态
    var goodsForm = $("#frmGoods");                                       //商品表单

    //获取搜索条件
    function getSearchItem() {
        goodsTypeId = $("#goodsType option:selected").val();                      //获取选中的商品类别
        goodsPublish = $("#goodsPublish option:selected").text();               //获取选中的商品状态（是否上架）
        goodsName = $("#goodsName").val();                                      //获取商品名称
        goodsNum = $("#goodsId").val();                                          //获取商品货号
        if (goodsPublish == "上架") {
            state = true;                                                       //如果状态为上架，传给接口参数state值为true
        } else if (goodsPublish == "下架") {
            state = false;                                                      //如果状态为下架，传给接口参数state值为false
        } else {
            state = "";                                                         //如果状态为上架，传给接口参数state值为“”
        }
    }

    //商品类别下拉框初始化
    function initGoodsTypeDropdown() {
        $.get("/goods", {}, function (data) {
            $(data.goodsTypeList).each(function (index, type) {                //为商品类别下拉框遍历赋值
                $(".goodsType").append($("<option></option>")                   //为商品类别下拉框增加option节点
                    .val(type.key)
                    .text(type.value)
                );
            });
        });
    }

    //商品状态下拉框初始化
    function initGoodsStateDropdown() {
        $.get("/goods", {}, function (data) {                                   //为商品状态下拉框遍历赋值
            $(data.stateList).each(function (index, state) {                    //为商品状态下拉框增加option节点
                $("#goodsPublish").append($("<option></option>")
                    .val(state.key)
                    .text(state.value)
                );
            });
        });
    }

    //表单中新旧程度下拉框初始化
    function initGoodsDegreeDropdown() {
        $.get("/goods", {}, function (data) {                                   //为新旧程度下拉框遍历赋值
            $(data.newDegreeList).each(function (index, degree) {                   //为新旧程度下拉框增加option节点
                $("#goodsDegreeForm").append($("<option></option>")
                    .val(degree.key)
                    .text(degree.value)
                );
            });
        });
    }

    //商品列表初始化
    function initGoodsList() {
        getSearchItem();
        var params = {
            goodsTypeId: goodsTypeId,
            state: state,
            title: goodsName,
            articleNumber: goodsNum,
            page: currentPage,                        //当前页数
            pageSize: pageSize,                       //每页记录条数
        };
        if (currentPage <= totalPage) {
            $.get("/goods/searchGoods", params, function (data) {
                var goodsList = data.items;                                      //获取所有商品信息
                console.log(goodsList);
                totalPage = data.totalPage;                                      //获取总页数
                $(goodsList).each(function (index, goods) {
                    var goodsInfo = $("#goodsContainer").clone();                //克隆一条商品记录
                    goodsInfo.show();
                    goodsInfo.find("input[type='checkbox']").attr("name", "checkBox");
                    goodsInfo.find("td:eq(1)").text(index+1);                      //给该条商品信息赋值商品id
                    goodsInfo.find("td:eq(2)").text(getLocalTime(goods.uptime));               //给该条商品信息赋值商品时间
                    goodsInfo.find("td:eq(3)").text(goods.typeName);             //给该条商品信息赋值商品类别
                    goodsInfo.find("td:eq(4)").text(goods.title);                //给该条商品信息赋值商品名称
                    goodsInfo.find("td:eq(5)").text(goods.articleNumber);        //给该条商品信息赋值商品货号
                    goodsInfo.find("td:eq(6)").text(goods.price);                //给该条商品信息赋值商品价格
                    goodsInfo.find("td:eq(7)").text(goods.state);                //给该条商品信息赋值商品状态
                    goodsInfo.find("td:eq(8)").text(goods.stockNumber);          //给该条商品信息赋值商品库存
                    goodsInfo.find("td:eq(10)").text(goods.id);                   //给该条商品信息赋值商品id
                    $(".container").append(goodsInfo);                           //在表单中添加商品记录
                });
                //编辑商品按钮
                $(".edit").click(function () {
                    var goodsId = $(this).next("td").text();            //获取当前商品的id
                    initUpdateGoodsForm(goodsId);                                 //初始化表单
                    $(".modal-footer button:eq(1)").attr("disable", "true")       //提交新建表单按钮隐藏且不可用
                        .hide();
                    $(".modal-footer button:eq(2)").attr("disable", "false")      //提交编辑表单按钮隐藏且不可用
                        .show();
                });
            });
        }
    }

    //根据搜索条件查找商品
    function searchGoods() {
        $(".container").empty();
        currentPage=1;
        initGoodsList();

    }

    //商品详情表单取消按钮
    function cancelForm() {
        goodsForm.find("input").val("");
        goodsForm.find("textarea").val("");
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


    //修改商品表单初始化，表单显示商品信息
    function initUpdateGoodsForm(goodsId) {
        $.get("goods/getGoods", {goodsId: goodsId}, function (data) {
            console.log(data);
            $("#goodsNameForm").val(data.title);                      //在表单上显示当前商品的标题
            $("#goodsPriceForm").val(data.price);                     //在表单上显示当前商品的价格
            //n$("#goodsAddForm").val(data.address);                 //在表单上显示当前商品的地址
            $("#goodsDateForm").val(getLocalTime(data.purchaseTime));     //在表单上显示当前商品的购买日期
            $("#goodsEndDateForm").val(getLocalTime(data.releaseTime));            //在表单上显示当前商品的保质期
            $("#goodsDegreeForm").val(data.newDegree);               //在表单上显示当前商品的新旧程度
            $("#goodsInfoForm").val(data.goodsIntroduction);        //在表单上显示当前商品的商品介绍
            $("#goodsDetailForm").val(data.message);                //在表单上显示当前商品的商品信息
            $("#goodsIdForm").val(data.id);
        });
    }

    //商品表单初始化
    function initGoodsForm() {
        //点击商品详情表单取消按钮
        $(".modal-footer .btn.btn-default").click(function () {
            cancelForm();                                                 //点击取消按钮清空表单
        });

        //点击表单右上角叉按钮
        $(".modal-header button").click(function () {
            cancelForm();                                                  //点击右上角叉按钮清空表单
        });

        //点击新增商品表单页面提交按钮
        $(".modal-footer button:eq(1)").click(function () {
            var title = $("#goodsNameForm").val();
            var price = $("#goodsPriceForm").val();
            var address = $("#goodsAddForm").val();
            var purchaseTime = $("#goodsDateForm").val();
            var releaseTime = $("#goodsEndDateForm").val();
            var newDegree = $("#goodsDegreeForm").val();
            var goodsIntroduction = $("#goodsInfoForm").val();
            var message = $("#goodsDetailForm").val();
            var params = {
                title: title,
                price: price,
                //address:address,
                purchaseTime: purchaseTime,
                releaseTime: releaseTime,
                newDegree: newDegree,
                goodsIntroduction: goodsIntroduction,
                message: message
            };
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/goods/addGoods',
                processData: false,
                dataType: 'json',
                data: JSON.stringify(params),
                success: function () {
                    cancelForm();
                    $(".container").empty();
                    initGoodsList();
                },
                error: function () {
                    alert("添加商品失败！");
                }
            });

        });

        //点击修改商品详情表单页面提交按钮
        $(".modal-footer button:eq(2)").click(function () {
            var id = $("#goodsIdForm").val();
            var title = $("#goodsNameForm").val();
            var price = $("#goodsPriceForm").val();
            //var address=$("#goodsAddForm").val();
            var purchaseTime = $("#goodsDateForm").val();
            var releaseTime = $("#goodsEndDateForm").val();
            var newDegree = $("#goodsDegreeForm").val();
            var goodsIntroduction = $("#goodsInfoForm").val();
            var message = $("#goodsDetailForm").val();
            var params = {
                id: id,
                title: title,
                price: price,
                //address:address,
                purchaseTime: purchaseTime,
                releaseTime: releaseTime,
                newDegree: newDegree,
                goodsIntroduction: goodsIntroduction,
                message: message
            };
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/goods/updateGoods',
                processData: false,
                dataType: 'json',
                data: JSON.stringify(params),
                success: function () {
                    cancelForm();
                    $(".container").empty();
                    initGoodsList();
                },
                error: function () {
                    alert("修改商品失败！");
                }
            });
        });

        //表单中新旧程度下拉框初始化
        initGoodsDegreeDropdown();
    }

    //删除商品
    function deleteGoods() {
        var goodsIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var goodsId = $(this).parents("td.check").nextAll("#id").text();     //获取checkbox所在行的goodsId
            goodsIds += goodsId + ",";                                              //给string类型的goodsIds赋值
        });
        if (goodsIds !== "") {
            $.get("/goods/deleteSomeGoods", {ids: goodsIds}, function (data) {             //调用删除商品接口
                if (data.status == "success") {
                    $("#selectAll").removeAttr("checked");
                    $(".container").empty();                                                //清空商品列表
                    initGoodsList();                                   //重新加载页面
                } else if (data.status == "failure") {
                    alert("删除失败!");
                }
            });
        } else {
            alert("您还未选择商品！");
        }
    }

    //商品上架
    function onSell() {
        var goodsIds = "";
        $("input[name='checkBox']:checked").each(function () {                       //遍历选中的checkbox
            var goodsId = $(this).parents("td.check").nextAll("#id").text();       //获取checkbox所在行的goodsId
            goodsIds += goodsId + ",";
        });
        if (goodsIds !== "") {
            $.get("/goods/changeSomeGoods", {ids: goodsIds, state: true}, function (data) {
                if (data.status == "success") {
                    $("#selectAll").removeAttr("checked");
                    $(".container").empty();                                                //清空商品列表
                    initGoodsList();                                   //重新加载页面
                } else if (data.status == "failure") {
                    alert("更改商品状态失败!");
                }
            });
        } else {
            alert("您还未选择商品！");
        }

    }

    //商品下架
    function offSell() {
        var goodsIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var goodsId = $(this).parents("td.check").nextAll("#id").text();
            $(this).parents("td.check").nextAll("td:eq(6)").text("下架");
            $(this).removeAttr("checked");
            $("#selectAll").removeAttr("checked");
            goodsIds += goodsId + ",";
        });
        if (goodsIds !== "") {
            $.get("/goods/changeSomeGoods", {ids: goodsIds, state: false}, function (data) {
                if (data.status == "success") {
                    $("#selectAll").removeAttr("checked");
                    $(".container").empty();                                                //清空商品列表
                    initGoodsList();                                   //重新加载页面
                } else if (data.status == "failure") {
                    alert("更改商品状态失败!");
                }
            });
        } else {
            alert("您还未选择商品！")
        }
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

    //初始化分页按钮
    function initPageBtn() {
        //显示当前页数
        $("#page").text(currentPage);
        //点击首页
        $("#btn1").click(function () {
            currentPage = 1;                               //点击首页时参数currentPage为0
            $(".container").empty();                       //清空表单数据
            initGoodsList();                               //传参并调用初始化表单方法
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
                initGoodsList();        //传参并调用初始化表单方法
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
                initGoodsList();          //传参并调用初始化表单方法
                $("#page").text(currentPage);
            }
        });

        //点击尾页
        $("#btn4").click(function () {
            currentPage = totalPage;                            //点击尾页时参数currentPage为0
            $(".container").empty();                            //清空表单数据
            initGoodsList();                //传参并调用初始化表单方法
            $("#page").text(currentPage);
        });

    }


    function init() {
        //商品类型下拉框初始化
        initGoodsTypeDropdown();
        //商品状态下拉框初始化
        initGoodsStateDropdown();
        //商品列表初始化
        initGoodsList();
        //商品表单初始化
        initGoodsForm();
        //分页按钮初始化
        initPageBtn();

        //点击搜索按钮事件
        searchBtn.click(function () {
            searchGoods();
        });

        //点击第一行的复选框控制全选和全不选
        $("#selectAll").click(function () {
            $("input[name='checkBox']").prop("checked", $(this).prop("checked"));                 //所有的付款框的状态和第一行复选框状态一致
        });

        //新建商品按钮
        $(".btn-toolbar a").click(function () {
            $(".modal-footer button:eq(2)").attr("disable", "true")        //点击新建按钮表单内第二个按钮隐藏且不可用
                .hide();
            $(".modal-footer button:eq(1)").attr("disable", "false")       //点击新建按钮表单内第一个按钮显示且可用
                .show();
        });

        //点击删除按钮
        deleteBtn.click(function () {
            deleteGoods();             //点击删除按钮删除商品
        });

        //点击上架按钮
        onSellBtn.click(function () {
            onSell();                   //点击上架按钮商品状态变成上架
        });

        //点击下架按钮
        offSellBtn.click(function () {
            offSell();                   //点击下架按钮商品状态变成下架
        });

        //点击打印按钮
        printBtn.click(function () {
            alert("打印");
        });

        //点击导出按钮事件
        exportExcelBtn.click(function () {
            exportExcel();                //点击导出按钮商品状态变成下架
        });

    }

    init();
});