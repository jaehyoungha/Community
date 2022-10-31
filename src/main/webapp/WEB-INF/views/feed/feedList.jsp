<%@ page import = "java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.ReviewDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.FeedDTO" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    session.setAttribute("marketId", "ha");

    List<FeedDTO> fList = (List<FeedDTO>) request.getAttribute("fList");

    if (fList == null) {
        fList = new ArrayList<>();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>피드</title>
</head>
<body>
<h2>피드</h2>
<hr/>
<br/>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">제목</div>
            <div class="divTableHead">내용</div>
            <div class="divTableHead">등록자</div>
            <div class="divTableHead">등록일</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (FeedDTO fDTO : fList) {
                if (fDTO == null) {
                    fDTO = new FeedDTO();
                }
        %>
        <div class="divTableRow">
            <div class="divTableCell">
                <%
                    String html = "";

                    html += (fDTO.getFeedSeq());

                %><%= html%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(fDTO.getMarketId()) %>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(fDTO.getFeedContents()) %>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(fDTO.getRegId()) %>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(fDTO.getRegDt()) %>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
<a href="/feedRegForm">[글쓰기]</a>
</body>
</html>