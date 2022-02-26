<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2022/1/10
  Time: 11:08 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>照片分类</title>
</head>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/all.js"></script>

<body>
<div id="sideBorder">

    <label >
        <span class="text-lg">添加照片</span>
        <input style=" margin: auto auto auto 40%;" type="file" id="image">
    </label>
    <br/>
    <button class="btn btn-success" onclick="upload('image','UploadPhoto','image')" >提交</button>
</div>


</body>

<script>
</script>

</html>
