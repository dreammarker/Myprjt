<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html><head><title>emp/getEmpList.jsp</title></head>
<body>
<a href="./empExcelView">엑셀뷰출력</a>
<c:forEach items="${empList}" var="emp">
	${emp.employeeId}   ${emp.firstName}  
	${emp.lastName} ${emp.Salary} <br>
</c:forEach>
</body>
</html>