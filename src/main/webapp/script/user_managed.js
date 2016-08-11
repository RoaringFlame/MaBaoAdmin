/**
 * Created by lies on 2016/8/9.
 */
"user strict";
$(function () {
    var currentPage = 1;
    var totalPage = 1;
    var pageSize = 4;
    var userRole = "";
    var userName = "";
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

    //获取用户搜索条件
    function getSearchItem() {
        userRole = $("#userType option:selected").val();                      //获取选中的商品类别
        userName = $("#userName").val();                                      //获取商品名称
    }

    //根据搜索条件查找用户
    function searchUser() {
        $("#container").empty();
        getSearchItem();
        currentPage=1;
        initUserList();
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

    //初始化员工信息列表
    function initUserList() {
        var params = {
            page: currentPage,                        //当前页数
            pageSize: pageSize,                       //页面大小
            searchKey:userName                        //用户名称
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
            $("#assortmentForm").val(data.id);                       //工号
            $("#categoryParent").val();                              //密码
            $("#assortmentDetail").val(data.name);                   //用户名
            $("#userTel").val(data.phone);                           //电话
            $("#userEmail").val(data.email);                         //邮箱

        });
    }

    //删除用户信息
    function deleteUser() {
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
            initUserList(currentPage, pageSize);           //传参并调用初始化表单方法
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

    //点击新增用户表单页面提交按钮
    $(".modal-footer button:eq(1)").click(function () {
        var password = $("#categoryParent").val();                  //密码
        var name = $("#assortmentDetail").val();                    //用户名
        var phone = $("#userTel").val();                            //电话
        var email = $("#userEmail").val();                          //邮箱
        var addParams = {
            name: name,
            password: password,
            phone: phone,
            email: email
        };
        if (newUserCheck(addParams) == 1) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/user/addUser',
            processData: false,
            dataType: 'json',
            data: JSON.stringify(addParams),
            success: function () {
                $("#container").empty();
                initUserList(currentPage, pageSize);
            },
            error: function () {
                alert("添加用户失败！");
            }
        });
    }
    });

    //新建用户校验
    function newUserCheck(addParams) {
        if(addParams.email !== "" && addParams.name !== "" && addParams.password !== "" && addParams.phone !== "") {
            var confirmPassword = $("#assortmentNum").val();             //确认密码
            if(confirmPassword !== addParams.password) {
                alert("您输入前后密码不一致！");
                return 0;
            }
            if(addParams.email.search(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/) !== 0) {
                alert("您的邮箱格式有问题！");
                return 0;
            }
            return 1;
        }   else {
            alert("请完善您的信息后再提交！");
        }
    }

    function init() {
        //点击第一行的复选框控制全选和全不选
        $("#selectAll").click(function () {
            $("input[name='checkBox']").prop("checked", $(this).prop("checked"));                 //所有的付款框的状态和第一行复选框状态一致
        });
        //点击搜索按钮事件
        $("#search").click(function () {
            searchUser();
        });
        //新建用户按钮
        $(".btn-toolbar a").click(function () {
            $(".modal-footer button:eq(2)").attr("disable", "true")        //点击新建按钮表单内第二个按钮隐藏且不可用
                .hide();
            $(".modal-footer button:eq(1)").attr("disable", "false")       //点击新建按钮表单内第一个按钮显示且可用
                .show();
        });
        //角色名下拉框初始化
        initRoleDropDownList();
        //初始化员工信息列表
        initUserList();
        //初始化分页按钮
        initPageBtn();
        //点击删除按钮实现删除
        $(".deleteUser").click(function () {
            deleteUser();
        });
    }

    init();
});

