<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>妈宝后台|发货单</title>

    <!-- Bootstrap -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/master.css" rel="stylesheet">

</head>
<body>


    <!--面板-->
    <div class="panel panel-default">
        <!--功能操作-->
        <div class="panel-heading" style="height: 5rem;">
            <ol class="breadcrumb panel-title pull-left">
                <li>订单管理</li>
                <li class="active">发货单</li>
            </ol>

            <div class="btn-toolbar ">
                <div class="dropdown btn-group navbar-nav pull-right ">
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

                <div class="btn-toolbar ">
                    <div class="btn-group navbar-nav pull-right ">
                        <a class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="true">
                            删除
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
                        <label for="orderStatus">订单状态：</label>
                        <select name="" class="form-control" id="orderStatus">
                            <option selected="selected" value="">待确认</option>
                            <option value="">待付款</option>
                            <option value="">待发货</option>
                            <option value="-">已确认</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-default">搜索</button>
                    <button type="submit" class="btn btn-default">高级搜索</button>
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
                    <th>发货单流水号</th>

                    <th>订单号</th>

                    <th>下单时间</th>

                    <th>收货人</th>

                    <th>收货人</th>

                    <th>发货时间</th>

                    <th>操作人</th>

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

                    <td>11102324</td>

                    <td>10月10日</td>

                    <td>姜哲</td>

                    <td>10月11日</td>

                    <td>待确认</td>

                    <td>方红</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>2</td>

                    <td>11102325</td>

                    <td>10月9日</td>

                    <td>闫璇</td>

                    <td>10月10日</td>

                    <td>待付款</td>

                    <td>方红</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <th>3</th>

                    <td>11102326</td>

                    <td>10月8日</td>

                    <td>张雅婷</td>

                    <td>10月9日</td>

                    <td>待发货</td>

                    <td>方红</td>

                </tr>

                <tr class="odd gradeX">

                    <td>
                        <label>
                            <input type="checkbox" class="checkboxes" value="1"/>
                        </label>
                    </td>

                    <td>4</td>

                    <td>11102327</td>

                    <td>10月7日</td>

                    <td>林松</td>

                    <td>10月8日</td>

                    <td>已确认</td>

                    <td>方红</td>

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

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../../script/lib/jquery.1.10.2.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../script/lib/bootstrap/bootstrap.min.js"></script>
</body>
</html>
