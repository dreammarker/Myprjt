package com.yedam.web.board;

import java.util.List;

public interface CommentsService {
	public void insertComments(CommentsVO vo);
	public void updateComments(CommentsVO vo);
	public void deleteComments(CommentsVO vo);
	public List<CommentsVO> getCommentsList(CommentsVO vo);
	public CommentsVO getComments(CommentsVO vo);
}
