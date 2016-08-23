"use strict";
$(function () {
    //全局变量
    var currentPage = 1;
    var totalPage = 1;
    var pageSize = 7;
    var roleId = "";
    var adminName = "";

    //角色下拉框初始化
    function initRoleDropDownList() {
        $.get("role/getRoleSelector", {}, function (data) {
            $(data).each(function (index, role) {
                $("#userType").append($("<option></option>")
                    .val(role.id)
                    .text(role.roleName)
                );
            });
        });
    }

    //初始化管理员信息
    function initAdminList() {
        var params = {
            roleId: roleId,
            username: adminName,
            page: currentPage,                        //当前页数
            pageSize: pageSize                       //页面大小
        };
        if (currentPage <= totalPage) {
            $.get("admin/searchAdmin", params, function (data) {
                var adminList = data.items;                                     //获取所有商品信息
                totalPage = data.totalPage;                                     //获取总页数
                currentPage = data.currentPage;
                $(adminList).each(function (index, admin) {
                    var adminInfo = $("#adminContainer").clone();
                    adminInfo.show();
                    adminInfo.find("input[type='checkbox']").attr("name", "checkBox");
                    adminInfo.find("td:eq(1)").text(admin.empno);
                    adminInfo.find("td:eq(2)").text(admin.username);
                    adminInfo.find("td:eq(3)").text(admin.roleVO.roleName);
                    adminInfo.find("td:eq(4)").text(admin.createTime);
                    adminInfo.find("td:eq(5)").text(admin.count);
                    adminInfo.find("td:eq(6)").text(admin.operationTime);
                    adminInfo.find("td:eq(7)").text(admin.operation);
                    adminInfo.find("td:eq(9)").text(admin.id);
                    $("#container").append(adminInfo);
                });

                //编辑商品按事件
                $(".edit").click(function () {
                    var adminId = $(this).next("td").text();            //获取当前商品的id
                    initUpdateAdminForm(adminId);
                    $("#exampleModalLabel").text("编辑用户");
                    $(".modal-footer button:eq(1)").attr("disable", "true")       //
                        .hide();
                    $(".modal-footer button:eq(2)").attr("disable", "false")
                        .show();
                });
            });
        }
    }

    //修改管理员信息，表单数据初始化
    function initUpdateAdminForm(adminId) {
        $.get("admin/getAdmin", {adminId: adminId}, function (data) {
            $("#assortmentForm").val(data.empno);                          //在表单上显示工号
            $("#assortmentDetail").val(data.username);                   //在表单上显示姓名
            $("#categoryParent").val(data.password);                   //在表单上显示密码
            $("#assortmentNum").val(data.password);
            $("#userTel").val(data.tel);                                 //在表单上显示电话
            $("#userEmail").val(data.email);                         //在表单上显示邮件
            $("#adminIdForm").val(data.id);                               //获取当前商品的id

            $("#selection").empty();                                  //清空之前的下拉框数据
            $(data.roleVOList).each(function (index, roleVO) {       //为角色下拉框增加option节点
                $("#selection").append($("<option></option>")
                    .val(roleVO.id)
                    .text(roleVO.roleName)
                );
            });
            $("#selection").val(data.roleId);                            //选中当前角色
        });
    }

    //初始化分页按钮
    function initPageBtn() {
        //显示当前页数
        $("#page").text(currentPage);
        //点击首页
        $("#btn1").click(function () {
            currentPage = 1;                               //点击首页时参数currentPage为0
            $("#container").empty();                       //清空表单数据
            initAdminList();                               //调用初始化表单方法
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
                initAdminList();                            //调用初始化表单方法
                $("#page").text(currentPage);               //更新当前页数
            }
        });

        //点击下一页
        $("#btn3").click(function () {
            if ((currentPage == totalPage) || (totalPage == 1)) {                //如果当前页面是最后一页时，点击下一页弹出提示框
                alert("当前已经是最后一页了!");
            }
            else {
                $("#container").empty();                      //如果不是最后一页，点击下一页时清空表单
                currentPage++;                                //当前页数加1
                initAdminList();                              //调用初始化表单方法
                $("#page").text(currentPage);                 //更新当前页数
            }
        });

        //点击尾页
        $("#btn4").click(function () {
            currentPage = totalPage;                            //点击尾页时参数currentPage为0
            $("#container").empty();                            //清空表单数据
            initAdminList();                                    //调用初始化表单方法
            $("#page").text(currentPage);                       //更新当前页数
        });
    }

    //表单功能初始化
    function initSubitForm() {
        //点击取消按钮
        $(".modal-footer .btn.btn-default").click(function () {
            cancelForm();                                                 //点击取消按钮清空表单
        });

        //点击右上角叉按钮
        $(".modal-header button").click(function () {
            cancelForm();                                                  //点击右上角叉按钮清空表单
        });

        //点击新增用户表单提交按钮
        $(".modal-footer button:eq(1)").click(function () {
            sendSubmit("admin/addAdmin");
        });

        //点击修改用户信息表单提交按钮
        $(".modal-footer button:eq(2)").click(function () {
            sendSubmit("admin/updateAdmin");
        });
    }

    function sendSubmit(url) {
        var empno = $("#assortmentForm").val();                        //在表单上显示的工号
        var username = $("#assortmentDetail").val();                   //在表单上显示的姓名
        var password = $("#categoryParent").val();                    //在表单上显示的密码
        var rpassword = $("#assortmentNum").val();                    //重复密码用于验证
        var tel = $("#userTel").val();                                 //在表单上显示的电话
        var email = $("#userEmail").val();                            //在表单上显示的邮件
        var id = $("#adminIdForm").val();
        var roleId = $("#selection option:selected").val();          //获取当前角色的id
        //信息验证
        //信息包装
        var params = {
            id: id,
            empno: empno,
            username: username,
            password: password,
            tel: tel,
            email: email,
            roleId: roleId
        };
        sendAjax(url, params)
    }

    //发送ajax请求
    function sendAjax(url, params) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            processData: false,
            dataType: 'json',
            data: JSON.stringify(params),
            error: function () {
                alert("服务器连接错误！");
            },
            success: function () {                  //如果请求成功
                $("#exampleModal").modal('hide');   //隐藏模态框
                cancelForm();                       //清空表单数据
                $("#container").empty();            //清空列表数据
                initAdminList();                    //加载表单数据
            }
        });
    }

    //表单结束或取消
    function cancelForm() {
        var infoForm = $("#AdminInfoForm");
        infoForm.find("input").val("");
        infoForm.find("select").val("");
    }

    //根据搜索条件查找用户
    function searchAdmin() {
        roleId = $("#userType option:selected").val();          //获取选中的角色id
        adminName = $("#userName").val();                       //管理员姓名模糊搜索字段
        $("#container").empty();
        initAdminList();
    }

    //初始化新建管理员按钮
    function initNewAdmin(){
        $.get("role/getRoleSelector", {}, function (data) {
            $("#selection").empty();                       //清空之前的下拉框数据
            $(data).each(function (index, roleVO) {       //为角色下拉框增加option节点
                if (index > 0) {
                    $("#selection").append($("<option></option>")
                        .val(roleVO.id)
                        .text(roleVO.roleName));
                }
            });
        });
        $(".modal-footer button:eq(2)").attr("disable", "true")        //点击新建按钮表单内第二个按钮隐藏且不可用
            .hide();
        $(".modal-footer button:eq(1)").attr("disable", "false")       //点击新建按钮表单内第一个按钮显示且可用
            .show();
    }

    //删除选中管理员
    function deleteAdmin() {
        var adminIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var adminId = $(this).parents("td.check").nextAll("#id").text();       //获取checkbox所在行的adminId
            adminIds += adminId + ",";                                              //给string类型的adminIds赋值
        });
        if (adminIds !== "") {                                                        //如果选中商品
            $.get("admin/deleteSomeAdmin", {ids: adminIds}, function (data) {       //调用删除商品接口
                if (data.status == "success") {                                       //如果请求成功
                    $("#selectAll").removeAttr("checked");                            //去除全选框的选中状态
                    $("#container").empty();                                          //清空商品列表
                    initAdminList();                                                  //重新加载页面
                } else if (data.status == "failure") {                               //如果请求失败弹出警告框
                    alert("删除失败!");
                }
            });
        } else {
            alert("您还未选择管理员！");                    //如果没有选中弹出警告框
        }
    }

    function initRoleManage(){

    }

    function init() {
        //角色名下拉框初始化
        initRoleDropDownList();
        //员工信息初始化
        initAdminList();
        //分页初始化
        initPageBtn();
        //初始化表单按钮
        initSubitForm();

        //点击搜索按钮事件
        $("#search").click(function () {
            searchAdmin();
        });

        //新建用户按钮初始化
        $("#newAdmin").click(function () {
           initNewAdmin();
        });

        //删除用户按钮初始化
        $("#deleteAdmin").click(function () {
            deleteAdmin();
        });

        //角色管理按钮初始化
        $("#roleManage").click(function () {
            initRoleManage();
        });

        //点击第一行的复选框控制全选和全不选
        $("#selectAll").click(function () {
            //所有的复选框的状态和第一行复选框状态一致
            $("input[name='checkBox']").prop("checked", $(this).prop("checked"));
        });
    }

    init();
});
