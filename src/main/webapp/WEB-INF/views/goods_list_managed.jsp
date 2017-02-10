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
    <title>妈宝后台|商品列表</title>

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
<nav class="navbar navbar-default ">
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
                       aria-expanded="false">商品管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="admin/goods_list_managed">商品列表</a></li>
                        <li><a href="admin/goods_type_managed">商品分类</a></li>
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

<!--面板-->
<div class="col-xs-10">

    <div class="panel panel-default">
        <!--功能操作-->
        <div class="panel-heading" style="height: 5rem;">
            <ol class="breadcrumb panel-title pull-left">
                <li><span class="iconfont">&#xe610</span>商品管理</li>
                <li class="active">商品列表</li>
            </ol>

            <div class="btn-toolbar ">
                <div id="tool" class="dropdown btn-group  pull-right ">
                    <a class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="true">
                        <span class="iconfont">&#xe605</span>
                        工具

                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                        <li><a><span class="iconfont">&#xe60c</span>导入 Excel</a></li>
                        <li><a class="disabled"><span class="iconfont">&#xe60d</span>导出 Excel</a></li>
                    </ul>
                </div>
                <div id="operate" class="dropdown btn-group  pull-right ">
                    <a class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="true">
                        <span class="iconfont">&#xe606</span>
                        操作

                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                        <li><a ><span class="iconfont">&#xe602</span>删除</a></li>
                        <li><a ><span class="iconfont">&#xe604</span>上架</a></li>
                        <li><a ><span class="iconfont">&#xe608</span>下架</a></li>
                    </ul>
                </div>

                <div class="btn-toolbar ">
                    <div class="btn-group navbar-nav pull-right ">
                        <a class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                           data-whatever="@mdo"><span class="iconfont">&#xe601</span>
                            新建
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
                        <label for="goodsType"></label>
                        <select name="" class="form-control goodsType" id="goodsType">
                            <option selected="selected" value="">所有类别</option>

                        </select>

                    </div>
                    <div class="form-group">
                        <label for="goodsPublish"></label>
                        <select name="" class="form-control" id="goodsPublish">
                            <option selected="selected" value="">全部</option>

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="goodsName"></label>
                        <input type="text" class="form-control" id="goodsName" placeholder="商品名称"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="form-group">
                        <label for="goodsId"></label>
                        <input type="text" class="form-control" id="goodsId" placeholder="货号"
                               aria-describedby="basic-addon1">
                    </div>
                    <button type="button" class="btn btn-default" id="search">搜索</button>
                </div>
            </form>

            <!--条件查询表单END-->

            <!--表格-->
            <table class="table  text-center">
                <thead>
                <tr>
                    <th style="width:8px;">
                        <label>
                            <input id="selectAll" type="checkbox" class="group-checkable"
                                   data-set="#sample_2 .checkboxes"/>
                        </label>
                    </th>
                    <th class="text-center">编号</th>

                    <th class="text-center">商品类别</th>

                    <th class="text-center">商品名称</th>

                    <th class="text-center">货号</th>

                    <th class="text-center">价格</th>

                    <th class="text-center">上架</th>

                    <th class="text-center">库存</th>

                    <th class="text-center">
                        编辑
                    </th>

                </tr>

                </thead>

                <tr class="odd gradeX" id="goodsContainer" style="display: none;">
                    <td class="check">
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>7</td>

                    <td>婴儿车</td>

                    <td>高级扶手婴儿车</td>

                    <td>SK0001</td>

                    <td>90.00</td>

                    <td>等待</td>

                    <td>78</td>

                    <td class="edit" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                        <span class="iconfont">&#xe609</span>
                    </td>

                    <td id="id" style="display: none">1</td>
                </tr>

                <tbody class="container">

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
            <!--分页end-->
        </div>
        </div>
    </div>
</div>
<!--面板END-->

<!--商品表单-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">商品详情</h4>
            </div>
            <div class="modal-body">
                <form id="frmGoods">
                    <div class="form-group ">
                        <label for="goodsNameForm" class="control-label  label-half ">商品名称:
                            <input type="text" class="form-control" id="goodsNameForm" name="title">
                        </label>
                        <span class="red">*</span>

                        <label for="goodsOldPriceForm" class="control-label  label-half">商品原价:
                            <input type="text" class="form-control" id="goodsOldPriceForm" name="oldPrice">
                        </label>
                        <span class="red">*</span>

                        <label for="goodsPriceForm" class="  control-label label-half ">商品现价:
                            <input type="text" class="form-control" id="goodsPriceForm" name="price">
                        </label>
                        <span class="red">*</span>

                        <label for="babyTypeForm" class="control-label  label-half ">宝宝类型:
                            <select class="form-control" id="babyTypeForm" name="babyType">
                            </select>
                        </label>
                        <span class="red">*</span>

                        <label for="goodsTypeForm" class="control-label  label-half ">商品类型:
                            <select class="form-control goodsType" id="goodsTypeForm" name="goodsType">
                            </select>
                        </label>
                        <span class="red">*</span>

                        <label for="goodsBrandForm" class="control-label  label-half">商品品牌：
                            <input type="text" class="form-control" id="goodsBrandForm" name="goodsBrand">
                        </label>
                        <span class="red">*</span>


                        <label for="goodsDegreeForm" class="control-label label-half ">新旧程度:
                            <select class="form-control" id="goodsDegreeForm" name="newDegree">
                            </select>
                        </label>
                        <span class="red ">*</span>

                        <label for="goodsStockNumberForm" class="control-label label-half ">库存:
                            <input class="form-control" id="goodsStockNumberForm" name="stockNumber">
                        </label>
                        <span class="red ">*</span>

                        <div class="form-group">
                            <label for="goodsDetailForm" class="control-label  label-half">妈咪说:
                                <textarea class="form-control" id="goodsDetailForm" name="message"></textarea>
                            </label>
                        </div>
                        <label class="control-label label-half">上传图片：
                            <input type="file" accept="image/png,image/gif" id="uploadPhoto">
                        </label>
                        <input type="text" id="goodsIdForm" style="display: none">
                        <input type="text" id="userIdForm" style="display: none">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">提交</button>
                <button type="button" class="btn btn-primary" style="display: none">提交</button>
            </div>
        </div>

    </div>
</div>
<!--商品表单END-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="script/lib/jquery.1.10.2.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="script/lib/bootstrap/bootstrap.min.js"></script>
<script src="script/goods_list_managed.js"></script>

</body>
</html>

