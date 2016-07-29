/**
 * Created by maxu on 2016/7/29.
 */
"use strict";
$(function () {
    var exportExcel = $("ul.dropdown-menu").find("li:eq(1)");

    function init() {
        //导出Excel
        exportExcel.click(function () {
            var typeName = $("#goodsType option:selected").text();
            var goodsPublish = $("#goodsPublish option:selected").text();
            var goodsName = $("#goodsName").val();
            var goodsId = $("#goodsId").val();
            var state;
            if(typeName=="所有类别"){
                typeName="";
            }
            if (goodsPublish == "上架") {
                state = true;
            } else if (goodsPublish == "下架") {
                state = false;
            } else {
                state = "";
            }
            alert(typeName);
            alert(state);
            window.location="/goodsManager/export/dataGoods?typeName="+typeName+"&state="+state+"&title="+goodsName+"&articleNumber="+goodsId;
        });

    }

    init();
});