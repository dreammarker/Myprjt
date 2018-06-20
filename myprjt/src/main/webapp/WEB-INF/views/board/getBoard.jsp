<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>getBoard.jsp</title>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.2.1.min.js"> </script>
<script>
$(function(){
	//ajax로 게시글 로딩
	function getCommentsList()
	{
		var requestData = {"boardSeq":"${board.seq}"};
		$.ajax({
			url : "../getCommentsList",
			data : requestData,
			dataType : 'json',
			success : function(data){
				for(i=0;i<data.length;i++){
					var html = '<div class="article" id="'+
					data[i].seq+'">'+data[i].name+data[i].content+'</div>'
					$("#commentList").append(html);
				}
			}
		});
	}
	$("#btnCommentAdd").click(function(){
		var requestData = {"name": $("[name=name]").val(), 
							"content": $("[name=content]").val(),
							"boardSeq": "${board.seq}"};
//		requestData = $("#commentFrm").serialize();
	//	requestData =boardSeq = "${board.seq}>"
		$.ajax({
			url : "../insertComment",
			data : requestData,
			dataType : 'json',
			success : function(data){
					var html = '<div class="article" id="'+
					data.seq+'">'+data.name+data	.content+'</div>'
					$("#commentList").append(html);
			}
		});
	});
	getCommentsList();
});
</script>
</head>
<body>
등록후 확인<br>
${boardVO}
<hr>
	제목: ${board.title} <br>
	작성자: ${board.writer}<br>
	내용: ${board.content}<br>
	<a href="../updateBoard">수정</a>
	
	<hr>
	<div style="border:1px solid green">
		<form id="commentFrm">
			<input name="name" size="10">
			<input name="content" size="50">
			<input type="button" id="btnCommentAdd" value="댓글등록"/>
		</form>
	</div>
	<div id="commentList"></div>

</body>
</html>