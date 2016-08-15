/**
 * Created by Emma on 2016/8/15.
 * 侧边栏固定
 */
"use strict";
$(function () {
    function initPage(){
        var arr = [
            ['商品列表', 'goods_list_managed'],
            ['商品分类', 'goods_type_managed'],
            ['订单', 'order_managed'],
            ['发货单', 'invoices_managed'],
            ['转让订单', 'transfer_order_managed'],
            ['账户管理', 'user_managed'],
            ['查看日志', 'log_managed'],
        ];


        $('#myScrollspy').find('li>a').click(function () {
            var html = $(this).html();
            $('iframe').attr('src', function () {
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i][0] == html) {
                        return arr[i][1] + '.html';
                    }
                }
            })
        });

        $("#goods_manage").find("a").click(function(){
            $("#nav_change").find("li:eq(0)>ul>li:eq(2)>a").removeClass("show");
            $("#nav_change").find("li:eq(0)>a").text("商品管理");
            $("#nav_change").find("li:eq(0)>ul>li:eq(0)>a").text("商品列表");
            $("#nav_change").find("li:eq(0)>ul>li:eq(0)>a").click(function () {
                $('iframe').attr('src', function () {
                    return arr[0][1] + '.html';
                })
            });

            $("#nav_change").find("li:eq(0)>ul>li:eq(1)>a").text("商品分类");
            $("#nav_change").find("li:eq(0)>ul>li:eq(1)>a").click(function () {
                $('iframe').attr('src', function () {
                    return arr[1][1] + '.html';
                })
            });
        });

        $("#order_manage").find("a").click(function(){
            $("#nav_change").find("li:eq(0)>a").text("订单管理");
            $("#nav_change").find("li:eq(0)>ul>li:eq(0)>a").text("订单");
            $("#nav_change").find("li:eq(0)>ul>li:eq(0)>a").click(function () {
                $('iframe').attr('src', function () {
                    return arr[2][1] + '.html';
                })
            });

            $("#nav_change").find("li:eq(0)>ul>li:eq(1)>a").text("发货单");
            $("#nav_change").find("li:eq(0)>ul>li:eq(1)>a").click(function () {
                $('iframe').attr('src', function () {
                    return arr[3][1] + '.html';
                })
            });

            $("#nav_change").find("li:eq(0)>ul>li:eq(2)>a").addClass("show");
            $("#nav_change").find("li:eq(0)>ul>li:eq(2)>a").click(function () {
                $('iframe').attr('src', function () {
                    return arr[4][1] + '.html';
                })
            });

        });

        $("#user_manage").find("a").click(function(){
            $("#nav_change").find("li:eq(0)>ul>li:eq(2)>a").removeClass("show");
            $("#nav_change").find("li:eq(0)>a").text("用户管理");
            $("#nav_change").find("li:eq(0)>ul>li:eq(0)>a").text("账户管理");
            $("#nav_change").find("li:eq(0)>ul>li:eq(0)>a").click(function () {
                $('iframe').attr('src', function () {
                    return arr[5][1] + '.html';
                })
            });

            $("#nav_change").find("li:eq(0)>ul>li:eq(1)>a").text("查看日志");
            $("#nav_change").find("li:eq(0)>ul>li:eq(1)>a").click(function () {
                $('iframe').attr('src', function () {
                    return arr[6][1] + '.html';
                })
            });
        });
    }

    function init(){
        initPage();
    }
    init();
});