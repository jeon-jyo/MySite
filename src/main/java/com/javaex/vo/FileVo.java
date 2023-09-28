package com.javaex.vo;

public class FileVo {

	private int no;
	private String orgName;
	private String saveName;
	private String filePath;
	private long fileSize;
	
	public FileVo() {}

	public FileVo(String orgName, String saveName, String filePath, long fileSize) {
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileVo [no=" + no + ", orgName=" + orgName + ", saveName=" + saveName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + "]";
	}
	
}
