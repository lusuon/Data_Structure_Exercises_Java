<%--
  Created by IntelliJ IDEA.
  User: 54234
  Date: 2018-09-15
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add test</title>
</head>
    <body style="margin:50px">
    <div align="center"><h1>Add infomation</h1></div>
    <div align="center">
        <form action = "http://localhost:8080/JavaWebAppDemo/src/AddServlet">
            Name:<input type="text" name="user"><br>
            Gender:<input type="radio" name = "gender" checked value = "male"/>male
                <input type="radio" name = "gender" checked value = "male"/>female<br/>
            Class:<select name = "class">
                <option value="1707"> 1707 </option>
                <option value="1708"> 1708 </option>
                <option value="1709"> 1709 </option>
                <option value="1701"> 1701 </option>
            <input type="submit" name="login" value = "submit"><br>
        </select>
        </form>
    </div>
    </body>
</html>
