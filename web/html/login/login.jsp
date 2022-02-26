<%--@elvariable id="document" type="document"--%>
<%--
  Author: mo7984130
  Date: 2022/1/30
  Time: 6:54 下午
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<body>
<div style="margin-top: 10%">

    <span>欢迎来到人才聚集地</span><br/>

    <label>
        <span>用户名</span>
        <input id="userName" type="text" class="form-control">
    </label>
    <br/>
    <label>
        <span>密码</span>
        <input id="password" type="password" class="form-control">
    </label>
    <br/>
    <button onClick="login()" class="btn btn-primary">登陆</button>

</div>


</body>

<script>

    function format(string , strings) {
        for (let i = 0 ; i < strings.length ; i++) {
            string = string.replace("%s",strings[i]);
        }
        return string;
    }

    function login(){

        let action = "${pageContext.request.contextPath}/Login?userName=%s&password=%s"
        action = format(action , [document.getElementById("userName").value , document.getElementById("password").value ]);

        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open("get", action , true);
        xmlHttp.send();
        xmlHttp.onreadystatechange = checkFunction;

        function checkFunction() {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                if (xmlHttp.responseText.indexOf("1") !== -1) {
                    alert("登陆成功! \n 关闭弹窗自动跳转到主页");
                    location.assign("../index/index.html");
                } else if (xmlHttp.responseText.indexOf("0") !== -1) {
                    alert("登陆失败! \n 用户名/密码错误！");
                }
            }
        }
    }

</script>

</html>
