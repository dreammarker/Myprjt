package com.yedam.web.emp.view;

import java.util.ArrayList;
import java.util.List;

import org.mvel2.optimizers.impl.refl.collection.ListCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.web.board.CommentListVO;
import com.yedam.web.board.CommentsService;
import com.yedam.web.board.CommentsVO;

@Controller
public class CommentController {

	@Autowired CommentsService commentService;
	
	@RequestMapping("/ajaxTest")
	@ResponseBody
	public List<String> test(){
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("이순신");
		list.add("을지문덕");
		return list;
	}
	
	//댓글추가
	@RequestMapping("/insertComment")
	@ResponseBody
	public CommentsVO insertComment(CommentsVO vo) {
		commentService.insertComments(vo);
		return commentService.getComments(vo);
	}
	
	
	//수정
	@RequestMapping("/updateComment")
	@ResponseBody
	public CommentsVO updateComment(CommentsVO vo) {
		commentService.updateComments(vo);
		return commentService.getComments(vo);
	}
	//삭제

	@RequestMapping("/deleteComment")
	@ResponseBody
	public CommentsVO deleteComment(CommentsVO vo) {
		commentService.deleteComments(vo);
		return commentService.getComments(vo);
	}
	//목록
	@RequestMapping("/getCommentsList")
	@ResponseBody
	public List<CommentsVO> getCommentsList(CommentsVO vo){
		return commentService.getCommentsList(vo);
	}
	//단건
	@RequestMapping("/getComments")
	@ResponseBody
	public CommentsVO getComments(CommentsVO vo) {
		return commentService.getComments(vo);
	}
	
	//xml 목록 조회
	@RequestMapping("/getCommentsXml")
	@ResponseBody
	public CommentListVO getCommentsXml(CommentsVO vo) {
		List<CommentsVO> list =commentService.getCommentsList(vo);
		CommentListVO listVO = new CommentListVO();
		listVO.setCommentList(list);
		return listVO;
	}
	
	
}
