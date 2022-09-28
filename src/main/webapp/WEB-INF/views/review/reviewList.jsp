<%@ page import="java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.ReviewDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    session.setAttribute("userId","ha");

    List<ReviewDTO> rList = (List<ReviewDTO>) request.getAttribute("rList");

    if (rList == null) {
        rList = new ArrayList<>();

    }

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>리뷰</title>
</head>
<body>
<h2>리뷰</h2>
<hr/>
<br/>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">순번</div>
            <div class="divTableHead">제목</div>
            <div class="divTableHead">내용</div>
            <div class="divTableHead">조회수</div>
            <div class="divTableHead">등록자</div>
            <div class="divTableHead">등록일</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (ReviewDTO rDTO : rList) {
                if (rDTO == null) {
                    rDTO = new ReviewDTO();
                }
        %>
        <div class="divTableRow">
            <div class="divTableCell">
                <%
                    String html = "";

                        html += (rDTO.getReviewSeq());

                %><%= html%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getTitle()) %>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getContents()) %>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getReadCnt()) %>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getUserId()) %>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getRegDt()) %>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
<a href="/reviewReg">[글쓰기]</a>
</body>
</html>