/**
 * Created by maxu on 2016/8/3.
 */
"user strict";
$(function () {
    var currentPage = 0;
    var totalPage = 1;
    var pageSize = 7;
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

    //初始化员工信息列表
    function initUserList() {
        var params = {
            page: currentPage,                        //当前页数
            pageSize: pageSize,                       //每页记录条数
        };
        if (currentPage < totalPage) {
            $.get("/user/list", params, function (data) {
                var userList = data.items;                                     //获取所有商品信息
                console.log(userList);
                totalPage = data.totalPage;                                     //获取总页数
                $(userList).each(function (index, user) {
                    var userInfo = $("#userContainer").clone();               //克隆一条商品记录
                    userInfo.show();
                    userInfo.find("input[type='checkbox']").attr("name", "checkBox");
                    userInfo.find("td:eq(1)").text(user.id);                   //给该条商品信息赋值商品id
                    userInfo.find("td:eq(2)").text(user.name);               //给该条商品信息赋值商品时间
                    userInfo.find("td:eq(3)").text(user.role);             //给该条商品信息赋值商品类别
                    userInfo.find("td:eq(4)").text(user.createTime);                //给该条商品信息赋值商品名称
                    userInfo.find("td:eq(5)").text(user.loginTime);        //给该条商品信息赋值商品货号
                    userInfo.find("td:eq(6)").text(user.lastOptTime);                //给该条商品信息赋值商品价格
                    userInfo.find("td:eq(7)").text(user.optContent);          //给该条商品信息赋值商品库存
                    $("#container").append(userInfo);                           //在表单中添加商品记录
                });
                //编辑商品按钮
                $(".edit").click(function () {
                    var goodsId = $(this).prevAll("td:eq(7)").text();            //获取当前商品的id
                    //initUpdateGoodsForm(goodsId);                                 //初始化表单
                    $(".modal-footer button:eq(1)").attr("disable", "true")       //
                        .hide();
                    $(".modal-footer button:eq(2)").attr("disable", "false")
                        .show();
                });
            });
        }
    }

    //删除用户信息
    function deleteGoods() {
        var userIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var userId = $(this).parents("td.check").next("td:eq(0)").text();     //获取checkbox所在行的goodsId
            userIds += userId + ",";                                              //给string类型的goodsIds赋值
        });
        if (userIds !== "") {
            $.get("/user/deleteSomeGoods", {userIds: userIds}, function (data) {             //调用删除商品接口
                if (data.status == "success") {
                    $("#selectAll").removeAttr("checked");
                    $(".container").empty();                                                //清空商品列表
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
            currentPage = 0;                               //点击首页时参数currentPage为0
            $(".container").empty();                       //清空表单数据
            initUserList(currentPage, pageSize);          //传参并调用初始化表单方法
        });

        //点击上一页
        $("#btn2").click(function () {
            if (currentPage == 0) {
                alert("当前已经是首页了!");                 //如果当前页面是首页，点击上一页弹出提示框
            }
            else {
                $(".container").empty();                    //如果不是首页，点击上一页时清空表单
                currentPage--;                              //当前页数减1
                initUserList(currentPage, pageSize);        //传参并调用初始化表单方法
            }
        });

        //点击下一页
        $("#btn3").click(function () {
            if ((currentPage == totalPage - 1) || (totalPage == 0)) {                //如果当前页面是最后一页时，点击下一页弹出提示框
                alert("当前已经是最后一页了!");
            }
            else {
                $(".container").empty();                      //如果不是最后一页，点击下一页时清空表单
                currentPage++;                                //当前页数加1
                initUserList(currentPage, pageSize);          //传参并调用初始化表单方法
            }
        });

        //点击尾页
        $("#btn4").click(function () {
            currentPage = totalPage - 1;                            //点击尾页时参数currentPage为0
            $(".container").empty();                            //清空表单数据
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