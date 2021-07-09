package kr.co.swingsaver.utils;

public enum ResponseCode {
	
	SUCCESS(0, "success"),
	EMPTY  (1, "empty data"),
	FAIL   (9, "fail");
	
	public int status;
	public String message;
	
	ResponseCode(int code, String status) {
		this.status = code;
		this.message = status;
	}
	
	public static ResponseCode findByStatus(int status) {
		for(ResponseCode enm : ResponseCode.values()) {
			if (enm.status == status) return enm;
		}
		return null;
	}
	
	public static ResponseCode findByMessage(String message) {
		for(ResponseCode enm : ResponseCode.values()) {
			if (enm.message.equals(message)) return enm;
		}
		return null;
	}
}
