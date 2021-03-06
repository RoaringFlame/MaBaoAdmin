<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>妈宝后台|用户管理</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/master.css" rel="stylesheet">
    <link href="css/themes.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!--<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <!--<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <![endif]-->
</head>
<body class="login-background">

<!-- 顶部导航 -->
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <!-- 导航切换-->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- 商标 -->
            <a class="navbar-brand" href="">
                <img alt="Brand" src="images/mabao_logo_min.png">
            </a>
            <!-- 商标END  -->

        </div>
        <!-- 导航切换END  -->

        <!-- 导航链接-->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">用户管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="admin/user_managed">账号管理</a></li>
                        <li><a href="admin/log_managed">查看日志</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><sec:authentication property="name"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="logout">注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- 导航链接END-->

    </div>
</nav>
<!-- 顶部导航END -->


<!--侧导航-->
<div class=" col-xs-2 " id="myScrollspy">
    <ul class="nav sidebar-box nav-stacked  affix" data-spy="affix" data-offset-top="125">
        <li class="first-level-menu">
            <a href="#goodsManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">
                <span class="iconfont">&#xe610</span>商品管理</a>
            <ul class="collapse nav text-indent-1 second-level-menu" id="goodsManaged">
                <li><a href="admin/goods_list_managed">商品列表</a></li>
                <li><a href="admin/goods_type_managed">商品分类</a></li>
            </ul>
        </li>

        <li class="first-level-menu">
            <a href="#orderManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">
                <span class="iconfont">&#xe60f</span>订单管理 </a>
            <ul class="collapse nav text-indent-1 second-level-menu" id="orderManaged">
                <li><a href="admin/order_managed">订单</a></li>
                <li><a href="admin/invoices_managed">发货单</a></li>
                <li><a href="admin/transfer_order_managed">转让订单</a></li>
            </ul>
        </li>
        <li class="first-level-menu">
            <a href="#userManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">
                <span class="iconfont">&#xe60e</span>用户管理</a>
            <ul class="collapse nav text-indent-1 second-level-menu" id="userManaged">
                <li><a href="admin/user_managed">账号管理</a></li>
                <li><a href="admin/log_managed">查看日志</a></li>
            </ul>
        </li>
    </ul>

</div>

<!--侧导航END-->

<div class="col-xs-10">
    <!--面板-->
    <div class="panel panel-default">

        <!--功能操作-->
        <div class="panel-heading" style="height: 5rem;">
            <ol class="breadcrumb panel-title pull-left">
                <li><span class="iconfont">&#xe60e</span>用户管理</li>
                <li class="active">账户管理</li>
            </ol>

            <div class="btn-toolbar ">

                <div class="btn-group navbar-nav pull-right ">
                    <a id="roleManage" class="btn btn-primary" data-toggle="modal" data-target="#roleModalForm" data-whatever="@mdo">
                        <span class="iconfont">&#xe601</span> 角色管理
                    </a>
                </div>

                <div class="dropdown btn-group navbar-nav pull-right ">

                    <a id="deleteAdmin" class="btn btn-default">
                        <span class="iconfont">&#xe602</span>删除
                    </a>
                </div>

                <div class="btn-toolbar ">
                    <div class="btn-group navbar-nav pull-right ">
                        <a id="newAdmin" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            <span class="iconfont">&#xe601</span> 新建
                        </a>
                    </div>
                </div>
            </div>

        </div>

        <!--功能操作END-->

        <div class="panel-body">
            <!--条件查询表单-->
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <div class="form-group">
                        <label for="userType">角色名：</label>
                        <select name="" class="form-control" id="userType">
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="userName">姓名：</label>
                        <input type="text" class="form-control" id="userName"
                               aria-describedby="basic-addon1">
                    </div>

                    <button type="button" class="btn btn-default" id="search">搜索</button>
                </div>
            </form>
            <!--条件查询表单END-->
            <!--表格-->
            <table class="table text-center">
                <thead>
                <tr>
                    <th style="width:8px;">
                        <label>
                            <input id="selectAll" type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </th>
                    <th class="text-center">工号</th>

                    <th class="text-center">姓名</th>

                    <th class="text-center">角色</th>

                    <th class="text-center">创建时间</th>

                    <th class="text-center">登录次数</th>

                    <th class="text-center">最后操作时间</th>

                    <th class="text-center">操作内容</th>

                    <th class="text-center">编辑</th>

                </tr>

                </thead>


                <tr class="odd gradeX" id="adminContainer" style="display: none;">

                    <td class="check">
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                    <td class="edit" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                        <span class="iconfont">&#xe609</span>
                    </td>
                    <td id="id" style="display: none"></td>
                </tr>
                <tbody id="container">
                </tbody>

            </table>
            <!--表格END-->
            <!--分页-->
            <div class="pagination" >
                <input type=button id="btn1" value="首页">
                <input type=button id="btn2" value="上一页">
                <input type=button id="btn3" value="下一页">
                <input type=button id="btn4" value="尾页">
                <span>当前页：<span id="page"></span></span>
            </div>
            <!--分页END-->
        </div>

    </div>
    <!--面板END-->
</div>


<!--用户表单-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button id="adminFormCancel" type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">新建用户</h4>
            </div>
            <div class="modal-body">

                <form class="form-group" id="adminInfoForm">
                    <label for="assortmentForm" class="control-label text-left label-half">工号:
                        <input type="text" class="form-control" id="assortmentForm">
                    </label>
                    <span class="red ">*</span>

                    <label for="categoryParent" class="control-label text-left label-half">密码:
                        <input type="password" class="form-control" id="categoryParent">
                    </label>
                    <span class="red ">*</span>

                    <label for="assortmentNum" class="  control-label text-left label-half">确认密码:
                        <input type="password" class="form-control" id="assortmentNum">
                    </label>
                    <span class="red ">*</span>

                    <label for="assortmentDetail" class="control-label text-left label-half">姓名:
                        <input type="text" class="form-control" id="assortmentDetail">
                    </label>
                    <span class="red ">*</span>

                    <label for="userTel" class="control-label text-left label-half">电话:
                        <input type="text" class="form-control" id="userTel">
                    </label>
                    <span class="red ">*</span>

                    <label for="userEmail" class="control-label text-left label-half">邮件:
                        <input type="text" class="form-control" id="userEmail">
                    </label>
                    <span class="red ">*</span>

                    <label for="assortmentDetail" class="control-label text-left label-half">角色类型:
                        <select class="form-control text-left " name="" id="selection">
                        </select>
                    </label>
                    <input type="text" id="adminIdForm" style="display: none">
                </form>


            </div>
            <div class="modal-footer">
                <button id="adminCancel" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="adminNewSubmit" type="button" class="btn btn-primary">提交</button>
                <button id="adminEditSubmit" type="button" class="btn btn-primary" style="display: none">提交</button>
            </div>
        </div>
    </div>
</div>
<!--用户表单END-->

<!--角色管理表单-->
<div class="modal fade" id="roleModalForm" tabindex="-1" role="dialog" aria-labelledby="roleModalTitle">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button id="roleFormCancel" type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="roleModalTitle">管理角色</h4>
            </div>
            <div class="modal-body">

                <form class="form-group" id="roleInfoForm">
                    <label for="newRolePic" class="control-label text-left label-half">
                        <input type="text" class="form-control" id="newRolePic" placeholder="请输入代号">
                    </label>
                    <label for="newRoleName" class="control-label text-left label-half">
                        <input type="text" class="form-control" id="newRoleName" placeholder="请输入角色名">
                    </label>
                    <button id="roleSubmit" type="button" class="btn btn-primary">确认添加</button>
                    <table class="table text-center">
                        <tr>
                            <th class="text-center">代号</th>
                            <th class="text-center">角色</th>
                            <th class="text-center">操作</th>
                        </tr>

                        <tr id="roleTable" style="display: none">
                            <td>admin</td>
                            <td>管理员</td>
                            <td style="display: none;"><input type="text"></td>
                            <td style="display: none;"><input type="text"></td>
                            <td><span class="iconfont edit-btn cadetblue" style="display: none;">&#xe604</span>
                                <span class="iconfont edit-btn cadetblue">&#xe609</span>
                                <span class="iconfont edit-btn orangered">&#xe602</span></td>
                        </tr>

                        <tbody id="roleContainer">
                        </tbody>
                    </table>

                </form>


            </div>
            <%--<div class="modal-footer">--%>
                <%--<button id="roleCancel" type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
                <%--<button id="roleConfirm" type="button" class="btn btn-primary">确认</button>--%>
            <%--</div>--%>
        </div>
    </div>
</div>
<!--角色管理表单END-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="script/lib/jquery.1.10.2.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="script/lib/bootstrap/bootstrap.min.js"></script>
<script src="script/user_managed.js"></script>
</body>
</html>
