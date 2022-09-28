<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>리뷰 작성</title>

</head>
<body>
<h2>리뷰 작성</h2>
<hr/>
<br/>
<form method="post" action="/reviewInsert">
    <div>제목 : <input type="text" name="title">
    </div>

    <div>내용 :
        <textarea name="contents"
                   style="
                   width: 200px; height: 200px"></textarea>
    </div>
<input type="submit" value="등록"/>
</form>
<iframe name="ifrPrc" style="display:none"></iframe>
</body>
</html>