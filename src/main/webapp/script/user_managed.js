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
    //选中项的遍历
    var checkedItems;
    //用户名
    var username;
    //表格的隐藏
    var hideGoods=$('#hideGoods').find('tr');
    //用户列表
    var userTable = $("#userTable").find("tbody");
    //给后台传递的数据
    params={
        roleId:roleId,
        username:username,
        page:page,
        pageSize:pageSize
    };

    //信息展示列表初始化
    function initUserList(){
        $.get("admin/searchAdmin",params, function (data) {
            console.log(data);
            //获取页面中需要的数据信息并展示
            $(data.items).each(function (index, userList) {
                //克隆隐藏列表中的信息
                var usersList = hideGoods.clone();
                usersList.find("input[name='checkOne']").attr('type', 'checkbox');
                //序号
                usersList.find("td:eq(1)").text(index+1);
                //工号展示
                usersList.find("td:eq(2)").text(userList.roleVO.id);
                //姓名展示
                usersList.find("td:eq(3)").text(userList.username);
                //角色展示
                usersList.find("td:eq(4)").text(userList.roleVO.roleName);
                //创建时间展示
                usersList.find("td:eq(5)").text(userList.createTime);
                //登陆次数展示
                usersList.find("td:eq(6)").text(userList.count);
                //最后操作时间展示
                usersList.find("td:eq(7)").text(userList.operationTime);
                //操作内容展示
                usersList.find("td:eq(8)").text(userList.operation);
                userTable.append(usersList);
            })
        });
    }
    //选中所有的实现
    function checkAll(){
        $("#checkAll").click(function () {
            $("input[name='checkOne']").prop("checked", $(this).prop("checked"));
        });
    }
    //删除
    function deleteSome(){
        $("#delBtn").click(function () {
           //获取选中项
            var roleId;
            var roleIds='';
            checkedItems=$("input[name='checkOne']:checked");
            if(checkedItems.val()){//如果为选中项
                $(checkedItems).each(function () {
                roleId=$(this).parents("tr").find("td:eq(2)").text();
                    console.log(roleId);
                roleIds+=roleId+",";
                console.log(roleIds);
                });
                $.get("admin/deleteSomeAdmin",{ids:roleIds}, function (data) {
                    $(this).parents("tr").remove();//从页面删除选中内容
                });
                window.location.reload();
            } else {
                alert("请至少选择一项才能进行删除操作！");//如果没有选中项提示没有选中不能删除
            }
        });
    }
    //新建用户
    function createUser(){

    }
    //搜索
    function searchOne(){
        $("#searchBtn").click(function () {
            $(userTable).empty();//表格内容清空
            roleId="";
            username="";
            initUserList();
        });
    }
    //编辑事件
    function changeOne(){

    }
    //角色管理
    function managedRole(){

    }
    //分页的实现
    function changePage(){

    }
    //页面初始化
    function initPages(){
        //选中所有的实现
        checkAll();
        //信息展示列表初始化
        initUserList();
        //删除
        deleteSome();
        //新建用户
        createUser();
        //搜索
        searchOne();
        //编辑事件
        changeOne();
        //角色管理
        managedRole();
        //分页的实现
        changePage();
    }
    //函数初始化
    function init(){
        initPages();
    }
   init();
});