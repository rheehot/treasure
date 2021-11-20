<%--
  Created by IntelliJ IDEA.
  User: kimsunho
  Date: 2021/08/16
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    //JSP Code
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-with,initial-scale=1">
    <title>$</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function () {

        });
    </script>


</head>
<body>
<div class="container">
    <form role="form" action="/insert" method="post">
        <label>title</label>
        <input type="text" class="form-control" name="title">
        <label>content</label>
        <input type="text" class="form-control" name="content">
        <label>writer</label>
        <input type="text" class="form-control" name="writer">
        <button type="submit" class="btn btn-secondary">저장</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/list'">목록으로</button>
    </form>
</div>

</body>
</html>
