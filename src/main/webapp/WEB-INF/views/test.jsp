<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="../script/lib/jquery.1.10.2.js"></script>
    <title>test</title>
    <script>
        function test() {
            var mydata ={
                id:61,title:"sabi a1",user_id:7,newDegree:"NINE",message: "中国",
                price:5,purchaseTime:2016-06-20,releaseTime:2016-06-21,goodsIntroduction:"hhhhhh3"
            };

            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: 'http://localhost:8080/goods/updateGoods',
                processData: false,
                dataType: 'json',
                data: JSON.stringify(mydata),
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
