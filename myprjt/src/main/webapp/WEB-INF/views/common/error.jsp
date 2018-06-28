<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body bgcolor="#224421" text="red">
<h3>기본에러화면입니다.</h3>
<br><br><br><br>
<!-- 에러 메세지 -->
message : ${exception.message}
</body>
</html>