<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>updateBoard.jsp</title>
</head>
<body>
<h3>게시글수정</h3>
<form action="./insertBoard" method="post">
<input type="text" name="title" value="${sessionScope.board.title}">
<input type="text" name="content" value="${board.content}">
<input type="text" name="writer" value="${board.writer}">
<input type="submit" value="수정">
</form>
${board}
</body>
</html>