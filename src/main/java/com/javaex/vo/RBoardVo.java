package com.javaex.vo;

public class RBoardVo {
	
	private int no;
	private UserVo userNo;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int groupNo;
	private int orderNo;
	private int depth;
	private String show;
	
	public RBoardVo() {}

	public RBoardVo(int no, UserVo userNo, String title, String content, int hit, String regDate, int groupNo,
			int orderNo, int depth, String show) {
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.show = show;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public UserVo getUserNo() {
		return userNo;
	}
	public void setUserNo(UserVo userNo) {
		this.userNo = userNo;
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
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "RBoardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", regDate=" + regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth
				+ ", show=" + show + "]";
	}
}
