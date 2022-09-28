<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    //전달받은 메시지
    String alt_title = CmmUtil.nvl((String)request.getAttribute("alt_title"));
    String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
    String alt_state = CmmUtil.nvl((String)request.getAttribute("alt_state"));
    String url = CmmUtil.nvl((String)request.getAttribute("url"));
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>COMG</title>
    <script src="/js/jquery-3.6.0.min.js"></script>


</head>
<body>

</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
    swal('<%=alt_title%>', "<%=msg%>", '<%=alt_state%>')
        .then(function(){
            location.href="/<%=url%>";
        });
</script>
</html>