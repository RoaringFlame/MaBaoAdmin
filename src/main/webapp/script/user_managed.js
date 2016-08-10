/**
 * Created by lies on 2016/8/9.
 */
"user strict";
$(function () {
    var currentPage = 1;
    var totalPage = 1;
    var pageSize = 2;
    //角色名下拉框初始化
    function initRoleDropDownList() {
        $.get("/user", {}, function (data) {
            $(data).each(function (index, userType) {
                $(".userType").append($("<option></option>")
                    .val(userType.key)
                    .text(userType.value)
                );
            });
        });
    }

    function getLocalTime(jsondate) {
        jsondate = "" + jsondate + "";                                    //因为jsonDate是number型的indexOf会报错
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

    //初始化员工信息列表
    function initUserList() {
        var params = {
            page: currentPage,                        //当前页数
            pageSize: pageSize
        };
        if (currentPage <= totalPage) {
            $.get("/user/searchUserName", params, function (data) {
                var userList = data.items;                                     //获取所有商品信息
                console.log(userList);
                totalPage = data.totalPage;                                     //获取总页数
                $(userList).each(function (index, user) {
                    var userInfo = $("#userContainer").clone();               //克隆一条商品记录
                    userInfo.show();
                    userInfo.find("input[type='checkbox']").attr("name", "checkBox");
                    userInfo.find("td:eq(1)").text(user.id);                        //给该条商品信息赋值商品id
                    userInfo.find("td:eq(2)").text(user.name);                      //给该条商品信息赋值商品时间
                    userInfo.find("td:eq(3)").text(user.role);                      //给该条商品信息赋值商品类别
                    userInfo.find("td:eq(4)").text(getLocalTime(user.createTime));  //给该条商品信息赋值商品名称
                    userInfo.find("td:eq(5)").text(user.loginTime);                 //给该条商品信息赋值商品货号
                    userInfo.find("td:eq(6)").text(getLocalTime(user.lastOptTime)); //给该条商品信息赋值商品价格
                    userInfo.find("td:eq(7)").text(user.optContent);                //给该条商品信息赋值商品库存
                    $("#container").append(userInfo);                               //在表单中添加商品记录
                });
                //编辑商品按钮
                $(".edit").click(function () {
                    var userId = $(this).prevAll("td:eq(6)").text();            //获取当前商品的id
                    initUpdateUserForm(userId);
                    $(".modal-footer button:eq(1)").attr("disable", "true")       //
                        .hide();
                    $(".modal-footer button:eq(2)").attr("disable", "false")
                        .show();
                });
            });
        }
    }

    //修改用户表单初始化，表单显示用户信息
    function initUpdateUserForm(userId) {
        $.get("user/getUser", {userId: userId}, function (data) {
            console.log(data);
            $(this).parent().find("td:eq(1)").val(data.id);                      //在表单上显示当前商品的标题
            $("#goodsPriceForm").val(data.price);                     //在表单上显示当前商品的价格
            $("#goodsDateForm").val(data.purchaseTime);              //在表单上显示当前商品的购买日期
            $("#goodsEndDateForm").val(data.releaseTime);            //在表单上显示当前商品的保质期
            $("#goodsDegreeForm").val(data.newDegree);               //在表单上显示当前商品的新旧程度
            $("#goodsInfoForm").val(data.goodsIntroduction);        //在表单上显示当前商品的商品介绍
            $("#goodsDetailForm").val(data.message);                //在表单上显示当前商品的商品信息
            $("#goodsIdForm").val(data.id);
        });
    }

    //删除用户信息
    function deleteGoods() {
        var userIds = "";
        $("input[name='checkBox']:checked").each(function () {                    //遍历选中的checkbox
            var userId = $(this).parent().parent().parent().find("td:eq(1)").text();
            userIds += userId + ",";                                               //给string类型的goodsIds赋值
        });
        userIds = userIds.substr(0, userIds.length - 1);                               //把最后一个，去除。
        if (userIds !== "") {
            $.get("/user/deleteSomeUser", {userIds: userIds}, function (data) {             //调用删除商品接口
                if (data.status == "success") {
                    $("#selectAll").removeAttr("checked");
                    $("#container").empty();                                                //清空商品列表
                    initUserList(currentPage, pageSize);                                   //重新加载页面
                } else if (data.status == "failure") {
                    alert("删除失败!");
                }
            });
        } else {
            alert("您还未选择用户！");
        }
    }

    //初始化分页按钮
    function initPageBtn() {
        //点击首页
        $("#btn1").click(function () {
            currentPage = 1;                               //点击首页时参数currentPage为0
            $("#container").empty();                       //清空表单数据
            initUserList(currentPage, pageSize);          //传参并调用初始化表单方法
        });

        //点击上一页
        $("#btn2").click(function () {
            if (currentPage <= 1) {
                alert("当前已经是首页了!");                 //如果当前页面是首页，点击上一页弹出提示框
            }
            else {
                $("#container").empty();                    //如果不是首页，点击上一页时清空表单
                currentPage--;                              //当前页数减1
                initUserList(currentPage, pageSize);        //传参并调用初始化表单方法
            }
        });

        //点击下一页
        $("#btn3").click(function () {
            if ((currentPage >= totalPage ) || (totalPage == 1)) {                //如果当前页面是最后一页时，点击下一页弹出提示框
                alert("当前已经是最后一页了!");
            }
            else {
                $("#container").empty();                      //如果不是最后一页，点击下一页时清空表单
                currentPage++;                                //当前页数加1
                initUserList(currentPage, pageSize);          //传参并调用初始化表单方法
            }
        });

        //点击尾页
        $("#btn4").click(function () {
            currentPage = totalPage;                            //点击尾页时参数currentPage为0
            $("#container").empty();                            //清空表单数据
            initUserList(currentPage, pageSize);                //传参并调用初始化表单方法
        });
    }

    function init() {
        //角色名下拉框初始化
        initRoleDropDownList();
        //初始化员工信息列表
        initUserList();
        //初始化分页按钮
        initPageBtn();
        //点击删除按钮实现删除
        $(".deleteUser").click(function () {
            deleteGoods();
        });
    }

    init();
});

