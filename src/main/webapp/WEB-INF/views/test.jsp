<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="script/lib/jquery.1.10.2.js"></script>
    <title>test</title>
    <script>
        function test() {
            var myData ={
                id:5,
                typeName:"HN",
                units: "个",
                "description": "中国",
                "goodsNumber":"5"
            };
            //alert(myData.goodsNumber);
            /*$.ajaxSetup({
                contentType : 'application/json'
            });
            $.post('http://localhost:8080/goodsType/addGoodsType', myData,
                    function(data) {
                        alert("id");
                    }, 'json');
                    */
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: 'http://localhost:8080/goodsType/changeGoodsType',
                processData: false,
                dataType: 'json',
                data: JSON.stringify(myData),
                success: function (data) {
                    alert("id: ");
                },
                error: function () {
                    alert('Err...');
                }
            });
        };
    </script>
</head>
<body>
Hello World!
    <button id="test" onclick="test()">test</button>
</body>
</html>
