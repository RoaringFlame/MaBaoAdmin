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
    <title>妈宝后台|订单</title>

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
                       aria-expanded="false">订单管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="admin/order_managed">订单</a></li>
                        <li><a href="admin/invoices_managed">发货单</a></li>
                        <li><a href="admin/transfer_order_managed">转让订单</a></li>
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
                <li><span class="iconfont">&#xe60f</span>订单管理</li>
                <li class="active">订单</li>
            </ol>

            <div class="btn-toolbar ">
                <div class="dropdown btn-group navbar-nav pull-right ">
                    <a class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="true">
                        <span class="iconfont">&#xe605</span>工具

                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                        <li><a href="#"><span class="iconfont">&#xe60c</span>打印</a></li>
                        <li><a href="#"><span class="iconfont">&#xe60d</span>导出 Excel</a></li>
                    </ul>
                </div>

                <%--<div class="btn-toolbar ">--%>
                <%--<div class="btn-group navbar-nav pull-right ">--%>
                <%--<a class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"--%>
                <%--aria-haspopup="true" aria-expanded="true">--%>
                <%--<span class="iconfont">&#xe604</span>发货--%>
                <%--</a>--%>
                <%--</div>--%>
                <%--</div>--%>

            </div>

        </div>

        <!--功能操作END-->

        <div class="panel-body">
            <!--条件查询表单-->
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">

                    <div class="form-group">
                        <label for="orderNum"></label>
                        <input type="text" class="form-control" id="orderNum" placeholder="订单号"
                               aria-describedby="basic-addon1">
                    </div>

                    <%--<div class="form-group">
                        <label for="consigner"></label>
                        <input type="text" class="form-control" id="consigner" placeholder="收货人"
                               aria-describedby="basic-addon1">
                    </div>--%>

                    <div class="form-group">
                        <label for="goodsStatus">订单状态：</label>
                        <select name="" class="form-control" id="goodsStatus">
                            <option value="">所有状态</option>
                        </select>
                    </div>

                    <button type="button" class="btn btn-default">搜索</button>
                    <a class="btn btn-primary" data-toggle="modal" data-target="#searchForm"
                       data-whatever="@mdo">高级搜索
                    </a>
                </div>
            </form>
            <!--条件查询表单END-->


            <!--表格-->
            <table class="table text-center">
                <thead>
                <tr>
                    <th style="width:8px;">
                        <label>
                            <input type="checkbox" class="group-checkable"
                                   data-set="#sample_2 .checkboxes"/>
                        </label>
                    </th>
                    <th class="text-center">编号</th>

                    <th class="text-center">订单号</th>

                    <th class="text-center">下单时间</th>

                    <th class="text-center">收货人</th>

                    <th class="text-center">应付金额</th>

                    <th class="text-center">订单状态</th>

                </tr>

                </thead>

                <tr class="odd gradeX" style="display: none;" id="orderContainer">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>
                    <th>1</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>

                <tbody id="container">

                </tbody>

            </table>
            <!--表格END-->
            <!--分页-->
            <div class="pagination">
                <input type=button id="btn1" value="首页">
                <input type=button id="btn2" value="上一页">
                <input type=button id="btn3" value="下一页">
                <input type=button id="btn4" value="尾页">
                <span>当前页：<span id="page"></span></span>
            </div>
            <!--分页end-->
        </div>

    </div>
    <!--面板END-->
</div>

<!--表单高级搜索-->
<div class="modal fade" id="searchForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">高级索搜</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group ">
                        <label for="orderNumForm" class="control-label  label-half ">订单号:
                            <input type="text" class="form-control" id="orderNumForm">
                        </label>
                        <span class="red">*</span>

                        <label for="consignerForm" class="control-label  label-half">购货人:
                            <input type="text" class="form-control" id="consignerForm">
                        </label>
                        <span class="red">*</span>

                        <label for="addressForm" class="  control-label label-half ">地址:
                            <input type="text" class="form-control" id="addressForm">
                        </label>
                        <span class="red">*</span>

                        <label for="receiverForm" class="control-label  label-half ">收货人:
                            <input type="text" class="form-control" id="receiverForm">
                        </label>
                        <span class="red">*</span>

                        <label for="telForm" class="control-label  label-half">手机号:
                            <input type="text" class="form-control" id="telForm">
                        </label>
                        <span class="red">*</span>

                        <label for="orderStatusForm" class="control-label  label-half">订单状态:
                            <select class="form-control" name="goodsDegreeForm" id="orderStatusForm">
                                <option value="all">所有状态</option>
                            </select>
                        </label>
                        <span class="red">*</span>

                        <div class="form-group " style="padding-left: 10px;">
                            <label class=" control-label clear " style="float: left;">所在地：</label>
                            <label class=" control-label clear " style="width:80%;">
                                <select class="form-control select-right  " name="province">
                                    <option value="请选择" >请选择</option>
                                </select>
                                <select class="form-control select-right " name="city">
                                    <option value="请选择" >请选择</option>
                                </select>
                                <select class="form-control select-right " name="urban">
                                    <option value="请选择" >请选择</option>
                                </select>
                            </label>
                        </div>

                        <div class="form-group transfer-time clear">
                            <label for="transferDateForm" class=" control-label  ">转让时间:</label>
                            <input type="date" class="form-control " id="transferDateForm">

                            <label for="transferNedDateForm" class=" control-label  ">至:</label>
                            <input type="date" class="form-control " id="transferNedDateForm">

                        </div>

                    </div>
                </form>
            </div>
            <div class="modal-footer clear">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">提交</button>
            </div>

        </div>

    </div>
</div>
<!--表单高级搜索END-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="script/lib/jquery.1.10.2.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="script/lib/bootstrap/bootstrap.min.js"></script>
<script src="script/lib/bootstrap/collapse.js"></script>
<script src="script/order_managed.js"></script>
</body>
</html>

