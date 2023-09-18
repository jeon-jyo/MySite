package com.javaex.vo;

public class BoardVo {
	
	private int no;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private UserVo userNo;
	
	public BoardVo() {}
	
	public BoardVo(int no, String title, String content, int hit, String regDate, UserVo userNo) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public UserVo getUserNo() {
		return userNo;
	}
	public void setUserNo(UserVo userNo) {
		this.userNo = userNo;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + "]";
	}
}
