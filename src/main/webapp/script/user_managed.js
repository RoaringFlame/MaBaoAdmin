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

                //编辑管理员按钮初始化
                $(".edit").click(function () {
                    var adminId = $(this).next("td").text();            //获取当前用户的id
                    initUpdateAdminForm(adminId);
                    $("#exampleModalLabel").text("编辑用户");
                    $("#adminNewSubmit").attr("disable", "true")       //
                        .hide();
                    $("#adminEditSubmit").attr("disable", "false")
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
            if ((currentPage == totalPage) || (totalPage <= 1)) {                //如果当前页面是最后一页时，点击下一页弹出提示框
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
            if (totalPage > 0) {
                currentPage = totalPage;                            //点击尾页时参数currentPage为0
                $("#container").empty();                            //清空表单数据
                initAdminList();                                    //调用初始化表单方法
                $("#page").text(currentPage);                       //更新当前页数
            }
        });
    }

    //表单功能初始化
    function initSubmitFormBtn() {
        //用户表单功能初始化
        initAdminFormFunc();

        //角色管理表单功能初始化
        initRoleFormFunc();
    }

    //用户表单功能初始化
    function initAdminFormFunc() {
        //点击右上角叉按钮
        $("#adminFormCancel").click(function () {
            cancelForm();                                                  //点击右上角叉按钮清空表单
        });

        //点击取消按钮
        $("#adminCancel").click(function () {
            cancelForm();                                                 //点击取消按钮清空表单
        });


        //点击新增用户表单提交按钮
        $("#adminNewSubmit").click(function () {
            sendSubmit("admin/addAdmin");
            cancelForm();
        });

        //点击修改用户信息表单提交按钮
        $("#adminEditSubmit").click(function () {
            sendSubmit("admin/updateAdmin");
            cancelForm();
        });
    }

    //用户管理表单结束或取消
    function cancelForm() {
        var infoForm = $("#adminInfoForm");
        infoForm.find("input").val("");
        infoForm.find("select").val("");
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
        //发送成功方法
        function adminSendSuc() {
            $("#exampleModal").modal('hide');   //隐藏模态框
            cancelForm();                       //清空表单数据
            $("#container").empty();            //清空列表数据
            initAdminList();                    //加载表单数据
        }

        sendJsonAjax(url, params, adminSendSuc())
    }

    //发送ajax请求
    function sendJsonAjax(url, params, successFunc) {
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
            success: successFunc
        });
    }

    //根据搜索条件查找用户
    function searchAdmin() {
        roleId = $("#userType option:selected").val();          //获取选中的角色id
        adminName = $("#userName").val();                       //管理员姓名模糊搜索字段
        $("#container").empty();
        totalPage = 1;
        initAdminList();
    }

    //初始化新建管理员按钮
    function initNewAdmin() {
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
        $("#adminEditSubmit").attr("disable", "true")        //点击新建按钮表单内第二个按钮隐藏且不可用
            .hide();
        $("#adminNewSubmit").attr("disable", "false")       //点击新建按钮表单内第一个按钮显示且可用
            .show();
    }

    //删除选中管理员
    function deleteAdmin() {
        var adminIds = "";
        $("input[name='checkBox']:checked").each(function () {                     //遍历选中的checkbox
            var adminId = $(this).parents("td.check").nextAll("#id").text();       //获取checkbox所在行的adminId
            adminIds += adminId + ",";                                              //给string类型的adminIds赋值
        });
        if (adminIds !== "") {
            //如果选中商品
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

    //角色列表信息初始化
    function initRoleList() {
        $("#roleContainer").empty();
        $.get("role/getRoleSelector", {}, function (data) {
            $(data).each(function (index, roleVO) {
                if (index > 0) {
                    var roleInfo = $("#roleTable").clone();
                    roleInfo.show();
                    roleInfo.find("td:eq(0)").text(roleVO.name);
                    roleInfo.find("td:eq(1)").text(roleVO.roleName);
                    roleInfo.find("td:eq(2)").find("input").val(roleVO.name);
                    roleInfo.find("td:eq(3)").find("input").val(roleVO.roleName);
                    roleInfo.find("td:eq(4)").find("span:eq(0)").click(function () {
                        var name = roleInfo.find("td:eq(2)").find("input").val();
                        var roleName = roleInfo.find("td:eq(3)").find("input").val();
                        editRole(roleVO.id, name, roleName)
                    });
                    roleInfo.find("td:eq(4)").find("span:eq(1)").click(function () {
                        roleInfo.find("td:eq(0)").hide();
                        roleInfo.find("td:eq(1)").hide();
                        roleInfo.find("td:eq(4)").find("span:eq(1)").hide();
                        roleInfo.find("td:eq(2)").show();
                        roleInfo.find("td:eq(3)").show();
                        roleInfo.find("td:eq(4)").find("span:eq(0)").show();
                    });
                    roleInfo.find("td:eq(4)").find("span:eq(2)").click(function () {
                        deleteRole(roleVO.id);
                    });
                    $("#roleContainer").append(roleInfo);
                }
            });
        });
    }

    //添加角色
    function addRole() {
        var name = $("#newRolePic").val();
        var roleName = $("#newRoleName").val();
        var flag = false;                   //校验标记
        if (name != "" && roleName != "") {
            flag = true;
        } else {
            flag = false;
            alert("请完善信息")
        }
        var params = {name: name, roleName: roleName};
        if (flag) {
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: 'role/addRole',
                processData: false,
                dataType: 'json',
                data: JSON.stringify(params),
                error: function () {
                    alert("服务器连接错误！");
                },
                success: function () {
                    $("#newRolePic").val("");
                    $("#newRoleName").val("");
                    initRoleList();
                }
            });
        }
    }

    //修改角色
    function editRole(roleId, name, roleName) {
        var flag = false;                   //校验标记
        if (name != "" && roleName != "") {
            flag = true;
        } else {
            flag = false;
            alert("请完善信息")
        }
        var params = {id: roleId, name: name, roleName: roleName};
        if (flag) {
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: 'role/updateRole',
                processData: false,
                dataType: 'json',
                data: JSON.stringify(params),
                error: function () {
                    alert("服务器连接错误！");
                },
                success: function () {
                    initRoleList();
                }
            });
        }
    }

    //删除角色
    function deleteRole(roleId) {
        $.get("role/deleteRole", {roleId: roleId}, function (data) {
            if (data.status == "success") {                                       //如果请求成功
                initRoleList();                                                  //重新加载页面
            } else if (data.status == "failure") {                               //如果请求失败弹出警告框
                alert("删除失败!");
            }
        });
    }

    //角色表单功能初始化
    function initRoleFormFunc() {
        //点击确认添加按钮
        $("#roleSubmit").click(function () {
            addRole();
        });

        //点击确认按钮
        $("#roleConfirm").click(function () {
            $("#roleModalForm").modal('hide');                      //隐藏模态框
        });
    }

    //初始化页面按钮
    function initPageButton() {
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
            initRoleList();
        });

        //点击搜索按钮初始化
        $("#search").click(function () {
            searchAdmin();
        });
        //分页按钮初始化
        initPageBtn();

        //点击第一行的复选框控制全选和全不选
        $("#selectAll").click(function () {
            //所有的复选框的状态和第一行复选框状态一致
            $("input[name='checkBox']").prop("checked", $(this).prop("checked"));
        });

        //表单按钮初始化
        initSubmitFormBtn();
    }

    function init() {
        //角色名下拉框初始化
        initRoleDropDownList();
        //员工信息初始化
        initAdminList();
        //页面按钮初始化
        initPageButton();
    }

    init();
});
