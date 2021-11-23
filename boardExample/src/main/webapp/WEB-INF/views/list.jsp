<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>list$</title>

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

    <button type="button" style="float: right" onclick="location.href='insert'">등록</button>

    <table class="table table-stripe">
        <thead>
        <tr>
            <th>bno</th>
            <th>title</th>
            <th>regdate</th>
            <th>updateDate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="BoardDTO">
            <tr>
                <td>${BoardDTO.bno}</td>
                <td><a class="move" href='/detail/<c:out value="${BoardDTO.bno}"/>'> ${BoardDTO.title}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${BoardDTO.regDate}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${BoardDTO.updateDate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="btn-group pagination">
        <c:if test="${pageMaker.prev }">
            <li>
                <a href='<c:url value="/list?page=${pageMaker.startPage-1 }"/>'><i
                        class="fa fa-chevron-left"></i>이전</a>
            </li>
        </c:if>
        <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
            <li>
                <a href='<c:url value="/list?page=${pageNum }"/>'><i class="fa">${pageNum }</i></a>
            </li>
        </c:forEach>
        <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
            <li>
                <a href='<c:url value="/list?page=${pageMaker.endPage+1 }"/>'><i
                        class="fa fa-chevron-right"></i>다음</a>
            </li>
        </c:if>
    </ul>
</div>

</body>
</html>
