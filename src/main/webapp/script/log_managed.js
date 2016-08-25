"use strict";
$(function () {
    //全局变量
    var currentPage = 1;
    var totalPage = 1;
    var pageSize = 7;
    var username = "";
    var startTime = "";
    var endTime = "";

    function initHistoryList() {
        var params = {
            username: username,
            startStr: startTime,
            endStr: endTime,
            page: currentPage,                        //当前页数
            pageSize: pageSize                       //页面大小
        };
        if (currentPage <= totalPage) {
            $.get("history/searchHistory", params, function (data) {
                var historyList = data.items;
                totalPage = data.totalPage;
                currentPage = data.currentPage;
                $(historyList).each(function (index, historyVO) {
                    var history = $("#historyContainer").clone();
                    history.find("td:eq(0)").text(historyVO.username);
                    history.find("td:eq(1)").text(historyVO.operationTime);
                    history.find("td:eq(2)").text(historyVO.wechart);
                    history.find("td:eq(3)").text(historyVO.ipAddress);
                    history.find("td:eq(4)").text(historyVO.operation);
                    $("#container").append(history);
                });
            });
        }
    }

    //根据搜索条件查找用户
    function searchAdmin() {
        startTime = $("#startingDate").val();
        endTime = $("#endDate").val();
        username = $("#operator").val();
        $("#container").empty();
        totalPage = 1;
        initHistoryList();
    }

    //初始化分页按钮
    function initPageBtn() {
        //显示当前页数
        $("#page").text(currentPage);
        //点击首页
        $("#btn1").click(function () {
            currentPage = 1;                               //点击首页时参数currentPage为0
            $("#container").empty();                       //清空表单数据
            initHistoryList();                               //调用初始化表单方法
            $("#page").text(currentPage);                  //更新当前页数
        });

        //点击上一页
        $("#btn2").click(function () {
            if (currentPage == 1) {
                alert("当前已经是首页了!");                 //如果当前页面是首页，点击上一页弹出提示框
            }
            else {
                $("#container").empty();                    //如果不是首页，点击上一页时清空表单
                currentPage--;                              //当前页数减1
                initHistoryList();                            //调用初始化表单方法
                $("#page").text(currentPage);               //更新当前页数
            }
        });

        //点击下一页
        $("#btn3").click(function () {
            if ((currentPage == totalPage) || (totalPage <= 1)) {                //如果当前页面是最后一页时，点击下一页弹出提示框
                alert("当前已经是最后一页了!");
            }
            else {
                $("#container").empty();                      //如果不是最后一页，点击下一页时清空表单
                currentPage++;                                //当前页数加1
                initHistoryList();                              //调用初始化表单方法
                $("#page").text(currentPage);                 //更新当前页数
            }
        });

        //点击尾页
        $("#btn4").click(function () {
            if (totalPage > 0) {
                currentPage = totalPage;                            //点击尾页时参数currentPage为0
                $("#container").empty();                            //清空表单数据
                initHistoryList();                                    //调用初始化表单方法
                $("#page").text(currentPage);                       //更新当前页数
            }
        });
    }

    function init() {
        initHistoryList();
        initPageBtn();

        $("#search").click(function () {
            searchAdmin();
        });
    }

    init();

});