/**
 * 用户管理
 */
$(function () {
    //当前页面
    var page=0;
    //页数
    var totalPage=1;
    //每页展示数量
    var pageSize=7;
    //传递到后台的json数据
    var params;
    //角色id
    var roleId;
    //用户名
    var username;
    var hideGoods=$('#hideGoods').find('tr');
    //用户列表
    var userTable = $("#userTable").find("tbody");

    //信息展示列表初始化
    function initUserList(){
        params={
            roleId:roleId,
            username:username,
            page:page,
            pageSize:pageSize
        };
        $.get("admin/searchAdmin",params, function (data) {
            console.log(data);
            //获取页面中需要的数据信息并展示
            $(data.items).each(function (index, userList) {
                //克隆隐藏列表中的信息
                var usersList = hideGoods.clone();
                usersList.find("input[name='allCheck']").attr('type', 'checkbox');
                //工号展示
                usersList.find("td:eq(1)").text(userList.roleVO.id);
                //姓名展示
                usersList.find("td:eq(2)").text(userList.username);
                //角色展示
                usersList.find("td:eq(3)").text(userList.roleVO.name);
                //创建时间展示
                usersList.find("td:eq(4)").text(userList.createTime);
                //登陆次数展示
                usersList.find("td:eq(5)").text(userList.count);
                //最后操作时间展示
                usersList.find("td:eq(6)").text(userList.operationTime);
                //操作内容展示
                usersList.find("td:eq(7)").text(userList.operation);

                userTable.append(usersList);
            })
        });
    }


    //页面初始化
    //函数初始化
    function init(){
        initUserList();
    }
   init();
});