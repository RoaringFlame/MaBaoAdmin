<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>妈宝后台|商品分类</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/master.css" rel="stylesheet">
    <![endif]-->

    <script src="script/lib/jquery.1.10.2.js"></script>
    <script src="script/goods_type_managed.js"></script>
</head>
<body>

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
            <a class="navbar-brand" href="#">
                <img alt="Brand" src="#">
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
                        <li><a href="goods_list_managed.html">商品列表</a></li>
                        <li><a href="goods_type_managed.html">商品分类</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">admin <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">注销</a></li>
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
            <a href="index.html">首页</a>
        </li>
        <li class="first-level-menu">
            <a href="#goodsManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">商品管理</a>
            <ul class="collapse nav text-indent-1 second-level-menu " id="goodsManaged">
                <li><a href="goods_list_managed.html">商品列表</a></li>
                <li><a href="goods_type_managed.jsp">商品分类</a></li>
            </ul>
        </li>

        <li class="first-level-menu">
            <a href="#orderManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">订单管理 </a>
            <ul class="collapse nav text-indent-1 second-level-menu" id="orderManaged">
                <li><a href="order_managed.html">订单</a></li>
                <li><a href="invoices_managed.html">发货单</a></li>
                <li><a href="transfer_order_managed.html">转让订单</a></li>
            </ul>
        </li>
        <li class="first-level-menu">
            <a href="#userManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">用户管理 </a>
            <ul class="collapse nav text-indent-1 second-level-menu" id="userManaged">
                <li><a href="user_managed.html">账号管理</a></li>
                <li><a href="log_managed.html">查看日志</a></li>
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
                <li>商品管理</li>
                <li class="active">商品分类</li>
            </ol>


            <div class="btn-toolbar ">
                <div class="dropdown btn-group navbar-nav pull-right ">
                    <a class="btn btn-default" id="delBtn">
                        删除
                    </a>
                </div>

                <div class="btn-toolbar " id="createNew">
                    <div class="btn-group navbar-nav pull-right ">
                        <a class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">新建</a>
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
                        <input type="text" class="form-control" id="goodsType" placeholder="商品类别"
                               aria-describedby="basic-addon1">
                    </div>

                    <button id="searchByType" type="button" class="btn btn-default">搜索</button>
                </div>
            </form>
            <!--条件查询表单END-->


            <!--表格-->
            <table class="table table-bordered table-striped" id="tableListForm">
                <thead>
                <tr>
                    <th style="width:8px;">
                        <label>
                            <input type="checkbox" id="textSearch" class="group-checkable"
                                   data-set="#sample_2 .checkboxes"/>
                        </label>
                    </th>

                    <th>编号</th>

                    <th>商品类别</th>

                    <th>商品数量</th>

                    <th>数量单位</th>

                    <th>编辑</th>

                </tr>

                </thead>

                <tbody>

                <tr class="odd gradeX" id="delMsg">

                    <td>
                        <label>
                            <input name="allCheck" type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>1</td>

                    <td>玩具</td>

                    <td>1</td>

                    <td>个</td>

                    <td data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo" class="changeMsg">编辑</td>


                </tr>



                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input name="allCheck"  type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>3</td>

                    <td>玩具</td>

                    <td>1</td>

                    <td>个</td>

                    <td data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">编辑</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input name="allCheck"  type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>3</td>

                    <td>日用品</td>

                    <td>12</td>

                    <td>个</td>

                    <td data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">编辑</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input name="allCheck"  type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>3</td>

                    <td>文具</td>

                    <td>1</td>

                    <td>个</td>

                    <td data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">编辑</td>

                </tr>



                </tbody>

            </table>
            <!--表格END-->
        </div>

    </div>

    <!--分页-->
    <div style="text-align:center">
        <input type=button id="btn1" value="首页" >
        <input type=button id="btn2" value="上一页">
        <input type=button id="btn3" value="下一页" >
        <input type=button id="btn4" value="尾页" >
    </div>
    <!--分页end-->

</div>
<!--面板END-->

<!--商品类型表单-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">商品详情</h4>
            </div>
            <div class="modal-body">
                <form id="createNewForm" class="col-md-offset-4 form-label" >
                    <div class="form-group  ">
                        <label for="assortmentForm" class="control-label text-left">分类名称:
                            <input type="text" class="form-control" id="assortmentForm" name="assortmentForm">
                        </label>
                        <span class="red ">*</span>
                    </div>
                    <div class="form-group ">
                        <label for="assortmentNum" class="  control-label text-left ">数量单位:
                            <input type="text" class="form-control" id="assortmentNum" name="assortmentNum">
                        </label>
                        <span class="red ">*</span>
                    </div>
                    <div class="form-group ">
                        <label for="assortmentDetail" class="control-label text-left ">分类描述:
                            <textarea class="form-control" id="assortmentDetail" name="assortmentDetail"></textarea>
                        </label>
                        <span class="red ">*</span>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="dismiss-btn" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="submit-btn" type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<!--商品类型表单END-->




<script src="script/lib/jquery.1.10.2.js"></script>

<script src="script/lib/bootstrap/bootstrap.min.js"></script>

<script src="script/lib/bootstrap/collapse.js"></script>

</body>
</html>
