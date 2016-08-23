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
    <title>Bootstrap 101 Template</title>
    <title>妈宝后台|查看日志</title>

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
                       aria-expanded="false">admin <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="login">注销</a></li>
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
        <div class="panel-heading" style="height: 5rem;">
            <!--功能操作-->
            <ol class="breadcrumb panel-title pull-left">
                <li> <span class="iconfont">&#xe60e</span>用户管理</li>
                <li class="active">查看日志</li>
            </ol>
        </div>

        <!--功能操作END-->

        <div class="panel-body">
            <!--条件查询表单-->
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <div class="form-group">
                        <label for="officialAccounts">公众号：</label>
                        <select name="" class="form-control" id="officialAccounts">
                            <option selected="selected" value="">嘀嘀嘀的哒</option>
                            <option value="">噜噜噜</option>
                        </select>

                    </div>

                    <div class="form-group">
                        <label for="startingDate">操作时间：</label>
                        <input type="date" class="form-control" id="startingDate"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="form-group">
                        <label for="endDate">至</label>
                        <input type="date" class="form-control" id="endDate"
                               aria-describedby="basic-addon1">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </div>
            </form>
            <!--条件查询表单END-->
            <!--表格-->
            <table class="table text-center">
                <thead>
                <tr>

                    <th class="text-center">登录人员</th>

                    <th class="text-center">操作时间</th>

                    <th class="text-center">公众号</th>

                    <th class="text-center">IP地址</th>

                    <th class="text-center">操作内容</th>

                </tr>

                </thead>

                <tbody>

                <tr class="odd gradeX">

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                </tr>

                <tr class="odd gradeX">

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                    <td></td>

                </tr>


            </table>
            <!--表格END-->
            <!--分页-->
            <nav>
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <!--分页END-->
        </div>

    </div>
    <!--面板END-->
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="script/lib/jquery.1.10.2.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="script/lib/bootstrap/bootstrap.min.js"></script>
</body>
</html>
