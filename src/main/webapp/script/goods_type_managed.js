/**
 * Created by Emma on 2016/7/30.
 * 商品管理商品类别页面
 */
"use strict";
$(function () {

    <!--全局变量-->
    //新建类别表单div的获取
    var createNewForm=$("#createNewForm");
    var page=0;//当前页面
    var pageSize=0;//页面数
    var totalPage=1;//总页数

    <!--初始化新建相关-->
    function initCreateNew(){

        //分类名称的获取
        var typeName=createNewForm.find("input[name='assortmentForm']").val();
        //上级分类的获取
        //var typeId=createNewForm.find("input[name='categoryParent']").val();
        //数量单位的获取
        var num=createNewForm.find("input[name='assortmentNum']").val();
        //分类描述的获取
        var title=createNewForm.find("input[name='assortmentDetail']").val();
        //新建表单的初始化

        //var params={
        //    typeName:typeName,
        //    num:num,
        //    title:title
        //}
        $("#createNew").find("a").click(function(){
            var info=$("#createNew").find("a").text();
            if(info=="新建"){
                $("#exampleModal").find("input,textarea").val("");
                //新建页面点击取消按钮的控制
                $("#dismiss-btn").click(function (){
                    $("#exampleModal").find("input,textarea").val("");
                });
                //新建页面点击提交按钮的控制
                //$("#submit-btn").click(function (){
                //    $.get("url",params,function(data){
                //        if(createNewForm!=""&&num!=""&&title!=""){
                //            initGoodsTypeList();
                //        }else{
                //            alert("请将信息填写完整！");
                //        }
                //    })
                //});
            }
        });

    }

    //修改相关
    function initChangeMsg(){
        //获取到选中项的值并展示，取消时值不变原样返回，点击保存就提示是否修改，修改完成后显示
        //如果没有选中项提示没有选中不能修改
        //如果选中多项提示只能单项修改
        
    }

    //删除相关
    function  initDelete(){
        //var checkAll=$().checked();//选中所有


        //如果没有选中项提示没有选中不能删除
        $("#delBtn").click(function () {
            var checkOne=$("#tableListForm").find("input[name='allCheck']").is(":checked");//选中一项
            //获取选中项
            if(checkOne==true){
                $("#delMsg").remove();
            }else{
                alert("请至少选择一项才能删除！");
            }
        })

    }


    //商品类别名搜索
    function initSearchByType(){
        $("#searchByType").click(function () {
            initGoodsTypeList();
        });
    }

    //选中所有的功能
    function initCheckBoxAll(){
        $("#textSearch").click(function () {
            $("input[name='allCheck']").prop("checked",$(this).prop("checked"));
        });
    }


    //商品类别展示初始化
    function initGoodsTypeList(){
        //传参
        var searchKey=$("#textSearch").val();//搜索关键字的传入
        if(page<=totalPage){
            $.get("url","", function (data) {
                console.log(data);
                //商品编号展示
                $("#textSearch").find("tr:eq(1)>th:eq(1)").text("编号数值展示");
                //商品类别展示
                $("#textSearch").find("tr:eq(1)>th:eq(2)").text("商品类别展示");
                //商品数量展示
                $("#textSearch").find("tr:eq(1)>th:eq(3)").text("商品数量展示");
                //数量单位展示
                $("#textSearch").find("tr:eq(1)>th:eq(4)").text("数量单位展示");
            })
        }

    }




    //页面初始化
    function initTypePage(){
        $.get("url","", function (data) {
            //新建商品的表单
            initCreateNew();
            //商品类别展示初始化
            initGoodsTypeList();
            //商品类别名搜索
            initSearchByType();
        });
        //选中所有和取消
        initSearchByType();
    }

    //初始化函数
    function init(){
        //页面初始化
        initTypePage();
        //选中所有和取消
        initCheckBoxAll();
        //初始化新建表單
        initCreateNew();
        ////按钮点击表单事件
        //initChangeBtn();
        //删除按钮
        initDelete();
    }

    //调用初始化函数
    init();
});
