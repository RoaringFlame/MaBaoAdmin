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
    <title>妈宝后台|登录</title>

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

<div style="padding-top: 10%;">
    <div class="login">
        <div class=" form-group">
            <img src="images/mabao_logo.png" alt="brand">
            <!--<h4 class=" control-label">妈宝后台登录</h4>-->
        </div>

        <form class="form-horizontal " style="padding: 0 2rem" action="login" method="POST">
            <hr>
            <div class="form-group ">
                <label class="sr-only " for="userName">userName </label>
                <div class="input-group">
                    <div class="input-group-addon"> <span class="iconfont">&#xe607</span></div>
                    <input  name="username" type="text" class="form-control" id="userName" placeholder="账号">

                </div>

            </div>
            <div class="form-group">
                <label class="sr-only" for="Password">userName </label>
                <div class="input-group ">
                    <div class="input-group-addon"><span class="iconfont">&#xe600</span></div>
                    <input name="password" type="password" class="form-control" id="Password" placeholder="密码">
                </div>

            </div>
            <c:if test="${not empty error}">
                <p class="warning">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
            </c:if>
            <hr>
            <div class="form-group">
                <div class="col-sm-12">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> 记住密码
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                    <button type="submit" class="btn btn-default pull-right ">登录</button>
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
            </div>

        </form>

    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../script/lib/jquery.1.10.2.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../script/lib/bootstrap/bootstrap.min.js"></script>
</body>
</html>
