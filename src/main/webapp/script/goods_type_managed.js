/**
 * Created by Emma on 2016/7/30.
 * 商品管理商品类别页面
 */
"use strict";
$(function () {

    <!--全局变量-->
    //新建类别表单div的获取
    var createNewForm = $("#createNewForm");
    //当前页面
    var page=1;
    //每页展示的信息数目
    var pageSize = 7;
    //总页数
    var totalPage=1;
    //列表格式隐藏
    var hideGoods = $("#hideGoods").find("tr");
    //商品类型列表
    var tableListForm = $("#tableListForm").find("tbody");
    //商品类型Id
    var id;//一级分类Id
    var goodsTypeIntroduction;          //商品类型描述
    //选中项的遍历
    var checkedItems;
    var goodsIds = "";  //存储选中项的id值
    var params;//前后台参数传递

    //1,初始化新建商品类型
    function initCreateType() {
        //判断点击按钮为新建
        $("#createNew").find("a").click(function () {
            //新建页面点击提交按钮的控制
            $("#submit-btn").click(function () {
                var typeNameList = createNewForm.find("input[name='typeName']").val();
                //数量单位的获取
                var num = createNewForm.find("input[name='goodsTypeIntroduction']").val();
                //分类描述的获取
                var title = createNewForm.find("textarea[name='assortmentDetail']").val();
                var params = {
                    typeName: typeNameList,
                    units: num,
                    description: title
                };
                if (typeNameList != "" && num != "" && title != "") {
                    $.ajax({
                        type: 'POST',
                        contentType: 'application/json',
                        url: '/goodsType/addGoodsType',
                        processData: false,
                        dataType: 'json',
                        data: JSON.stringify(params),
                        success: function (data) {
                            initGoodsTypeList();
                            window.location.reload();
                        },
                        error: function () {
                            alert('新建失败！');
                        }
                    });
                } else {
                    alert('数据不完整！');
                }
                alert(data.message);
                typeNameList = createNewForm.find("input[name='typeName']").val();
                //数量单位的获取
                num = createNewForm.find("input[name='goodsTypeIntroduction']").val();
                //分类描述的获取
                title = createNewForm.find("textarea[name='assortmentDetail']").val();
                //清空新建的内容
            });
        });
    }

    function initDismisbtn(){
        //点击取消按钮的控制
        $("#dismiss-btn").click(function () {
            //取消事件清除输入的内容
            $("#exampleModal").find("input,textarea").val("");
        });
    }
    //选中所有的实现
    function initCheckBoxAll() {
        $("#textSearch").click(function () {
            $("input[name='allCheck']").prop("checked", $(this).prop("checked"));
        });
    }

    //2,初始化删除商品类型
    function initDelType() {
        //删除按钮的点击事件
        $("#delBtn").click(function () {
            //获取选中项
            var goodsId;
            checkedItems = $("input[name='allCheck']:checked");
            if (checkedItems.val()) {
                $(checkedItems).each(function () {                     //遍历选中的checkbox
                    goodsId = $(this).parents("tr").find("td:eq(5)").text();           //获取checkbox所在行顺序
                    goodsIds += goodsId + ",";
                    console.log(goodsIds);
                });
                $.get("/goodsType/deleteSomeGoodsType", {ids: goodsIds}, function (data) {
                    $(this).parents("tr").remove();//从页面删除选中内容
                });
                window.location.reload();
            } else {
                alert("请至少选择一项才能进行删除操作！");//如果没有选中项提示没有选中不能删除
            }
        });
    }

    //3，初始化商品类型搜索
    function initSearchKey() {
        //输入关键字，点击搜索按钮实现搜索，页面重新加载为搜索页面
        $("#searchByType").click(function () {
            $(tableListForm).empty();//表格内容清空
            initGoodsTypeList();
        });
    }


    //4，初始化编辑商品类型
    function initChangeType() {
        //编辑表单初始化
        $(".changeMsg1").click(function () {
            //获取编辑按钮点击的数据id
            var tId = $(this).prevAll(".typeId1").text();
            $.get("/goodsType/"+tId,function (data) {
                //商品类别名称
                $("#assortmentForm").val(data.typeName);
                //数量单位的获取
                $("#assortmentNum").val(data.units);
                //分类描述的获取
                $("#assortmentDetail").val(data.description);
            });

            //提交按钮的点击事件
            $("#submit-btn").click(function () {
                //商品类型的获取
                var typeNameList = createNewForm.find("input[name='typeName']").val();
                //数量单位的获取
                var num = createNewForm.find("input[name='goodsTypeIntroduction']").val();
                //分类描述的获取
                var title = createNewForm.find("textarea[name='assortmentDetail']").val();
                var params = {
                    id:tId,
                    typeName: typeNameList,//分类名称
                    units: num,//数量单位
                    description: title //分类描述
                };
                $.ajax({
                    type: 'POST',
                    contentType: 'application/json',
                    url: '/goodsType/changeGoodsType',
                    processData: false,
                    dataType: 'json',
                    data: JSON.stringify(params),
                    success: function (data) {
                        initGoodsTypeList();
                        window.location.reload();
                    },
                    error: function () {
                        console.log('修改失败！');
                    }
                });
            });
        });
    }

    //5,类型列表初始化
    function initGoodsTypeList() {
        params = {
            searchKey: $("#goodsType").val(),
            page: page,
            pageSize: pageSize
        };
        $.get("/goodsType/searchGoodsType", params, function (data) {
            //获取页面中需要的数据信息并展示
            $(data.items).each(function (index, goodsType) {
                //克隆隐藏列表中的信息
                var typeList = hideGoods.clone();
                typeList.find("input[name='allCheck']").attr('type', 'checkbox');
                //编号数值展示
                typeList.find("td:eq(1)").text(index+1);
                //商品类别展示
                typeList.find("td:eq(2)").text(goodsType.typeName);
                //商品数量展示
                typeList.find("td:eq(3)").text(goodsType.goodsNumber);
                //数量单位展示
                typeList.find("td:eq(4)").text(goodsType.units);
                typeList.find("td:eq(5)").text(goodsType.id);
                tableListForm.append(typeList);

            });
            $("#pageShow").text(page);
            initChangeType();
        });
    }

    //6,分页功能的实现
    function initPage() {
        $.get("/goodsType/searchGoodsType", params, function (data) {
            var totalPage = data.totalPage;
            //首页
            if (page >= 1 && page <= totalPage) {
                console.log(data);
                $("#btn1").click(function () {
                    if (page != 1) {
                        page = 1;
                        console.log(btn1);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList();
                    }
                });
                //上一页
                $("#btn2").click(function () {
                    console.log(btn2);
                    if (page > 1) {
                        page = page - 1;
                        console.log(btn2);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList();
                    }
                });
                //下一页
                $("#btn3").click(function () {
                    if (page < totalPage) {
                        page = page + 1;
                        console.log(btn3);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList();
                    }
                });
                //尾页
                $("#btn4").click(function () {
                    if (page != totalPage) {
                        page = totalPage;
                        console.log(btn4);
                        console.log(data);
                        $(tableListForm).empty();
                        initGoodsTypeList();
                    }
                });
            }
        });
    }


    //7,页面初始化
    function initGoodsTypePage() {
        //5,商品类型列表初始化
        initGoodsTypeList();
        //6,分页功能的实现
        initPage();
        //1,初始化新建商品类型
        initCreateType();
        //选中所有
        initCheckBoxAll();
        //2,初始化删除商品类型
        initDelType();
        //3，初始化商品类型搜索
        initSearchKey();
        //4，初始化编辑商品类型
        initChangeType();
        //取消按钮的控制
        initDismisbtn();
    }

    //8，初始化函数
    function init() {
        //7,页面初始化
        initGoodsTypePage();
    }

    //9,调用初始化函数
    init();

});
