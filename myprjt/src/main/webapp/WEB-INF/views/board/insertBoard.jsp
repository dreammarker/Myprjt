<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>insertBoard.jsp</title>
<script src="./ckeditor_4/ckeditor.js"></script>
</head>
<body>
<form action="./insertBoard" method="post" enctype="multipart/form-data" onsubmit="return submitCheck()" name="boardform">

제목<input type="text" name="title">
작성자<input type="text" name="writer">
내용<textarea name="content"  id="content"></textarea>

첨부파일<input type="file" name="tempuploadfile">
<input type="submit" value="등록">
</form>
<script>
	CKEDITOR.replace("content",{filebrowserUploadUrl:
		"${pageContext.request.contextPath}/fileUpload.jsp"});
	function submitCheck(){
		if(document.boardform.writer.value==""){
			alert("작성자 등록!!");
			document.boardform.writer.focus();
			return false;
		}
		if(document.boardform.title.value==""){
			alert("제목 등록!!");
			document.boardform.title.focus();
			return false;
		}
		var editor_data = CKEDITOR.instances.content.getData();
		if(editor_data==""){
			alert("내용 등록!!");
			document.boardform.content.focus();
			
			return false;
		}
		return true;
		
	}
	/* $("#boardform").submit(function())
	if($("[name=writer]").val()==""){
		
	} */
</script>
</body>
</html>