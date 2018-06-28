package com.yedam.web.board;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private String seq;
	private String title;
	private String writer;
	private String content;
	private String regDate;
	private int cnt;
	private String searchKeyword;
	private String searchCondition;
	
	private String uploadfile; //db에 저장될 파일명
	
	public MultipartFile getTempuploadfile() {
		return tempuploadfile;
	}
	public void setTempuploadfile(MultipartFile tempuploadfile) {
		this.tempuploadfile = tempuploadfile;
	}
	MultipartFile tempuploadfile; //임시 업로드 파일
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + ", searchKeyword=" + searchKeyword + ", searchCondition=" + searchCondition
				+ "]";
	}
	public String getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	
}
