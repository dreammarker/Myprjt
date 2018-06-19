<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>getBoard.jsp</title>
</head>
<body>
등록후 확인<br>
${boardVO }
<hr>
	제목: ${board.title} <br>
	작성자: ${board.writer}<br>
	내용: ${board.content}<br>
	<a href="../updateBoard">수정</a>
</body>
</html>