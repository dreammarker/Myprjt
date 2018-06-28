package com.yedam.web.board;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="commentList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentListVO {
	
	@XmlElement(name="comment")
	private List<CommentsVO> commentList;

	public List<CommentsVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentsVO> commentList) {
		this.commentList = commentList;
	}
	
	
}
