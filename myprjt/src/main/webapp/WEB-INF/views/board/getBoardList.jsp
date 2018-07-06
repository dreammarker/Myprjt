<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>

	<a href="./empExcelCreate">엑셀출력</a>
   <a href="insertBoardForm">버튼</a>

   <h3>제목이올시다</h3>
   <form>
      <select>
         <option>선택</option>
         <c:forEach items="${conditionMap}" var="item">
         <option value="${item.value}">
         ${item.key}</option>
         </c:forEach>
      </select>
      <input type="submit" value="제출">
   </form>
   <table>
   <tr>
   <th>번호</th> <th>제목</th> <th>작성자</th> <th>작성일</th></tr>
   <c:forEach items="${boardList}" var="board">
         <tr><td>${board.SEQ}</td>
            <td><a href="getBoard/${board.SEQ}">${board.TITLE}</a></td>
            <td>${board.WRITER}</td>
            <td>${board.REGDATE}</td>
        </tr>
   </c:forEach>
   </table>
   <!-- 페이징-->
   <my:paging paging="${paging}"> </my:paging>
	<script>
	function doList(page){
		location.href="./getBoardList?page="+page;
	}
	</script>
</body>
</html>