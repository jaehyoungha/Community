<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
    <li><a href="/notice">공지</a></li>
    <li><a href="/reviewList">리뷰</a></li>
    <li><a href="/pic">사진</a></li>
</ul>
</body>
</html>