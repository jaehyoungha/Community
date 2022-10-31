<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>피드 작성</title>
</head>
<body>
<h2>피드 작성</h2>
<hr/>
<br/>
<form method="get" action="/feedReg">
    <div>내용 :
        <textarea name="feedContents"
                  style="
                   width: 200px; height: 200px"></textarea>
    </div>
    <input type="submit" value="등록"/>
</form>
</body>
</html>