<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.FeedDTO" %>
<%
    String id =(String)session.getAttribute("MarketId");
    FeedDTO fDTO = (FeedDTO) request.getAttribute("fDTO");

    if (fDTO == null) {
        fDTO = new FeedDTO();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>일단 메인</title>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        li {
            float: left;
        }

        li a {
            display: block;
            padding: 8px;
            background-color: #dddddd;
        }
        li a.current {
            background-color: greenyellow;
            color: white;
        }
        li a:hover:not(.current) {
            background-color: #c4ffc4;
            color: white;
        }
    </style>
</head>
<body>
<ul>
    <li><a href="/main">홈</a></li>
    <li><a href="/feedList">공지</a></li>
    <li><a href="/reviewList">리뷰</a></li>
    <li><a href="/pic">사진</a></li>
    <%if (id != null) { %>
    <li><%=id%>님 환영합니다.</li>
<li><a href="/user/myPage">마이페이지</a></li>
    <li><a href="logout" onclick="confirmLogout();">로그아웃</a></li>
    <%} else {%>
    <li><a href="/user/loginPage">로그인하기</a></li>
    <li><a href="/user/signUpPage">회원가입하기</a></li>
    <%}%>
</ul>

</body>
</html>