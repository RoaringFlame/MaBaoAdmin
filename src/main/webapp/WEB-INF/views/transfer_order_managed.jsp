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
    <title>妈宝后台|转让订单</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/master.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!--<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <!--<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <![endif]-->
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
                <img alt="Brand" src="index.html">
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
                        <li><a href="order_managed.html">订单</a></li>
                        <li><a href="invoices_managed.html">发货单</a></li>
                        <li><a href="transfer_order_managed.html">转让订单</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">admin <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="login.html">注销</a></li>
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
               aria-expanded="false">商品管理</a>
            <ul class="collapse nav text-indent-1 second-level-menu" id="goodsManaged">
                <li><a href="admin/goods_list_managed">商品列表</a></li>
                <li><a href="admin/goods_type_managed">商品分类</a></li>
            </ul>
        </li>

        <li class="first-level-menu">
            <a href="#orderManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">订单管理 </a>
            <ul class="collapse nav text-indent-1 second-level-menu" id="orderManaged">
                <li><a href="admin/order_managed">订单</a></li>
                <li><a href="admin/invoices_managed">发货单</a></li>
                <li><a href="admin/transfer_order_managed">转让订单</a></li>
            </ul>
        </li>
        <li class="first-level-menu">
            <a href="#userManaged" data-toggle="collapse" role="button"
               aria-haspopup="true"
               aria-expanded="false">用户管理</a>
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
                <li>订单管理</li>
                <li class="active">转让订单</li>
            </ol>

            <div class="btn-toolbar ">
                <div class="dropdown btn-group  pull-right ">
                    <a class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="true">
                        工具
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                        <li><a href="#">打印</a></li>
                        <li><a href="#">导出 Excel</a></li>
                    </ul>
                </div>
                <div class="dropdown btn-group  pull-right ">
                    <a class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="true">
                        操作
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                        <li><a href="#">确认收货</a></li>
                        <li><a href="#">确认寄售</a></li>
                    </ul>
                </div>
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

                    <div class="form-group">
                        <label for="consigner"></label>
                        <input type="text" class="form-control" id="consigner" placeholder="收货人"
                               aria-describedby="basic-addon1">
                    </div>

                    <div class="form-group">
                        <label for="goodsStatus">订单状态：</label>
                        <select name="" class="form-control" id="goodsStatus">
                            <option selected="selected" value="">待确认</option>
                            <option value="">待付款</option>
                            <option value="">待发货</option>
                            <option value="-">已确认</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-default">搜索</button>
                    <a class="btn btn-primary" data-toggle="modal" data-target="#searchForm"
                       data-whatever="@mdo">高级搜索
                    </a>
                </div>
            </form>
            <!--条件查询表单END-->

            <!--表格-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th style="width:8px;">
                        <label>
                            <input type="checkbox" class="group-checkable"
                                   data-set="#sample_2 .checkboxes"/>
                        </label>
                    </th>
                    <th>编号</th>

                    <th>订单号</th>

                    <th>下单时间</th>

                    <th>寄货人</th>

                    <th>应付金额</th>

                    <th>订单状态</th>

                </tr>

                </thead>

                <tbody>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>1</td>

                    <td>
                        <span data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            11102324
                        </span>
                    </td>

                    <td>
                         <span data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            10月10日
                        </span>
                    </td>

                    <td>姜哲</td>

                    <td>100</td>

                    <td>待确认</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>2</td>

                    <td>
                       <span data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            11102325
                        </span>
                    </td>

                    <td>
                         <span data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            10月9日
                        </span>
                    </td>

                    <td>闫璇</td>

                    <td>78</td>

                    <td>待收货</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>3</td>

                    <td>
                        <span data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            11102326
                        </span>
                    </td>

                    <td>
                          <span data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            10月8日
                        </span></td>

                    <td>张雅婷</td>

                    <td>120</td>

                    <td>已确认</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>4</td>

                    <td>
                        <span data-toggle="modal" data-target="#exampleModal"
                              data-whatever="@mdo">
                            11102327
                        </span>
                    </td>

                    <td>
                        <span data-toggle="modal" data-target="#exampleModal"
                              data-whatever="@mdo">
                            10月7日
                        </span>
                    </td>

                    <td>林松</td>

                    <td>200</td>

                    <td>已确认</td>

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
                <form>
                    <div class="form-group ">

                        <label for="kind" class="control-label label-half ">宝物类型:
                            <select class="form-control text-left " name="kind" id="kind">
                                <option value="童车">童车</option>
                                <option value="安全座椅">安全座椅</option>
                                <option value="玩具">玩具</option>
                                <option value="家具">家具</option>
                                <option value="电器">电器</option>
                            </select>
                        </label>

                        <label for="brand" class="control-label label-half ">宝物品牌:
                            <select class="form-control text-left " name="brand" id="brand">
                                <option value="强生">强生</option>
                                <option value="好孩子">好孩子</option>
                                <option value="贝亲">贝亲</option>
                            </select>
                        </label>

                        <label for="date" class="control-label label-half ">购物时间:
                            <input class="form-control" type="date" name="date" id="date">
                        </label>

                        <label for="level" class="control-label label-half ">宝物品牌:
                            <select class="form-control text-left " name="level" id="level">
                                <option value="全新">全新</option>
                                <option value="9成新">9成新</option>
                                <option value="8成新">8成新</option>
                                <option value="6-7成新">6-7成新</option>
                                <option value="5成新以下">5成新以下</option>
                            </select>
                        </label>

                        <label for="size" class="control-label label-half ">宝物尺码:
                            <select class="form-control text-left " name="size" id="size">
                                <option value="0—3m">0-3m</option>
                                <option value="0—3m">3-6m</option>
                                <option value="6—9m">6-9m</option>
                                <option value="9—12m">9-12m</option>
                                <option value="12—18m">12-18m</option>
                                <option value="18—24m">18-24m</option>
                                <option value="2T">2T</option>
                                <option value="3T">3T</option>
                                <option value="4T">4T</option>
                                <option value="5T">5T</option>
                                <option value="6T">6T</option>
                            </select>
                        </label>

                        <label for="sex" class="control-label label-half ">适合宝宝:
                            <select class="form-control text-left " name="sex" id="sex">
                                <option value="男">男</option>
                                <option value="女">女</option>
                                <option value="所有宝宝">所有宝宝</option>
                            </select>
                        </label>

                        <label class="control-label label-half ">原装包装:
                            <input type="radio" name="packet" id="packet-on">有
                            <input type="radio" name="packet" id="packet-off">无
                        </label>

                        <label class="control-label label-half ">发票/小票:
                            <input type="radio" name="packet" id="invoice-on">有
                            <input type="radio" name="packet" id="invoice-off">无
                        </label>

                        <label for="oldPrice" class="control-label label-half ">购买原价:
                            <input type="text" class="form-control" id="oldPrice">
                        </label>

                        <label for="newPrice" class="control-label label-half ">转让现价:
                            <input type="text" class="form-control" id="newPrice">
                        </label>


                        <label for="message" class=" control-label label-half " style="width: 80%">
                            卖家寄语:宝物详情/使用心得/瑕疵情况/等
                            <textarea class=" form-control " name="" id="message"></textarea>
                        </label>

                        <!--上传照片-->
                        <div class="clearfix"></div>
                        <label class="label-half">
                            <input type="file" accept="image/png,image/gif" id="uploadPhoto">
                        </label>
                        <!--<img src="../img/1.png" alt="">-->
                        <!--<img src="../img/2.png" alt="">-->
                        <!--<img src="../img/3.png" alt="">-->

                        <!--上传照片END-->
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<!--商品表单END-->


<!--表单高级搜索-->
<div class="modal fade" id="searchForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">高级索搜</h4>
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
                                <option>ddd</option>
                                <option>ddd</option>
                            </select>
                        </label>
                        <span class="red">*</span>

                        <div class="form-group " style="padding-left: 10px;">
                            <label class=" control-label clear " style="float: left;">所在地：</label>
                            <label class=" control-label clear " style="width:80%;">
                                <select class="form-control select-right  " name="province">
                                    <option>ddd</option>
                                </select>
                                <select class="form-control select-right " name="city">
                                    <option>ddd</option>
                                </select>
                                <select class="form-control select-right " name="urban">
                                    <option>ddd</option>
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
</body>
</html>
