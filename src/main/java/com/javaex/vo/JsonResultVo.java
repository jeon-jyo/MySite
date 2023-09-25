package com.javaex.vo;

public class JsonResultVo {

	// 성공했을 때
	// success, List<GuestVo>, null
	// fail, null, "통신오류"
	
	// 필드
	private String result;		// 'success'	'fail'	success
	private Object data;    	// 성공했을 때 data        
	private String failMsg;		// 실패했을 때 참고할 수 있는 메시지 코드값
	
	// 생성자
	public JsonResultVo() {}
	
	public JsonResultVo(String result, Object data, String failMsg) {
		this.result = result;
		this.data = data;
		this.failMsg = failMsg;
	}
	
	// 메소드 - getter
	public String getResult() {
		return result;
	}
	public Object getData() {
		return data;
	}
	public String getFailMsg() {
		return failMsg;
	}
	
	// 메소드 - 일반
	public void success(Object data) {
		this.result = "success";
		this.data = data;
	}
	
	public void fail(String failMsg) {
		this.result = "fail";
		this.failMsg = failMsg;
	}

	@Override
	public String toString() {
		return "JsonResultVo [result=" + result + ", data=" + data + ", failMsg=" + failMsg + "]";
	}
	
}