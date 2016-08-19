<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>妈宝网页版介绍</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><img src="img/AppIcon_2.png" width="50px"></li>
                <li style="font-size: 20px;padding-left: 15px;padding-top: 10px"><span
                        style="color: #00a2d4;font-size: 23px">妈宝</span>|App下载
                </li>
            </ul>
        </div>
    </div>
</nav>
<!--主体介绍开始-->
<!--妈宝app下载引导-->
<div style="height: 700px;padding-top: 60px;background-color: #b9ecff">
    <div class="col-md-6" style="padding-top: 0;text-align: center;padding-left: 10%;">
        <div>
            <img src="img/mabao_logo.png">
        </div>
        <div style="float: left">
            <span style="font-size: 20px;">妈宝App宣言：</span>
            <span style="font-size:26px;color: blue">专属于妈妈和宝宝的网上商城</span>
        </div>
        <div style="padding-bottom: 10px;float: left">
            <span style="font-size: 20px;">妈宝App评分：</span>
            <img src="img/love.png" width="30px">
            <img src="img/love.png" width="30px">
            <img src="img/love.png" width="30px">
            <img src="img/love.png" width="30px">
            <img src="img/love.png" width="30px">
            <img src="img/love.png" width="30px">
        </div>
        <div style="border: 1px #bce8f1 solid;border-radius: 5px;padding: 10px;float: left;">
            <span style="font-size: 21px;">拿出手机，扫下方二维码，下载属于你的上上妈宝</span>
        </div>
        <div class="row">
            <div class="col-md-6">
                <img src="img/mm1.png" width="140px;"><br/>
                <div style="padding-top: 10px">
                <span style="font-size: 20px;padding-top: 10px">Android版下载</span><br/>
                </div>
                <div style="padding-top: 10px">
                <button class="btn btn-info">Android版下载</button>
                </div>
            </div>
            <div class="col-md-6">
                <img src="img/mm1.png" width="140px;"><br/>
                <div style="padding-top: 10px">
                    <span style="font-size: 20px;">IOS版下载</span><br/>
                </div>
                <div style="padding-top: 10px">
                <button class="btn btn-info" style="width: 117px">IOS版下载</button>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <!--轮播图-->
        <div id="myCarousel" class="carousel slide">
            <!-- 轮播（Carousel）项目 -->
            <div class="carousel-inner" >
                <div class="item active">
                    <img src="img/index.png" style="padding-left: 25%">
                </div>
                <div class="item">
                    <img src="img/per.png" style="padding-left: 25%">
                </div>
                <div class="item">
                    <img src="img/youlike.png" style="padding-left: 25%">
                </div>
                <div class="item">
                    <img src="img/selfup.png" style="padding-left: 25%">
                </div>
            </div>
        </div>
    </div>
<!--轮播图 END-->
</div>
</body>

<script src="script/lib/jquery.1.10.2.js"></script>
<script src="script/lib/bootstrap/bootstrap.min.js"></script>
<script src="script/description_app.js"></script>

</html>