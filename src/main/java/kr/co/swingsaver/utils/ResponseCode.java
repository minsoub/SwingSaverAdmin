package kr.co.swingsaver.utils;

public enum ResponseCode {
	//ALREADY_EXISTS_MOBILENO    (100, "이미 등록된 mobileNo 입니다"),
	//ALREADY_REQUEST_MOBILE_CODE(101, "이미 인증번호가 발송되었습니다"),
	//ALREADY_EXISTS_UUID        (102, "이미 등록된 uuid 입니다"),
	ALREADY_EXISTS_USERNAME    (103, "이미 등록된 계정명 입니다"),
	ALREADY_EXISTS_CODE        (104, "이미 등록된 코드명 입니다"),
	
	NOT_FOUND_ROLE_TYPE    (110, "정의되지 않은 ROLE_TYPE 입니다"),
	//NOT_FOUND_MOBILENO     (111, "mobileNo 를 찾을 수 없습니다"),
	//NOT_FOUND_UUID         (112, "uuid 를 찾을 수 없습니다"),
	NOT_FOUND_ACCESS_TOKEN (113, "access_token 을 찾을 수 없습니다"),
	NOT_FOUND_REFRESH_TOKEN(114, "refresh_token 을 찾을 수 없습니다"),
	NOT_FOUND_USER_INFO    (115, "사용자 정보를 찾을 수 없습니다"),
	
	EXPIRED_ACCESS_TOKEN (120, "access_token 이 만료되었습니다"),
	EXPIRED_REFRESH_TOKEN(121, "refresh_token 이 만료되었습니다"),

	INVALID_ACCESS_TOKEN (130, "유효하지 않은 access_token 입니다"),
	INVALID_REFRESH_TOKEN(131, "유효하지 않은 refresh_token 입니다"),
	INVALID_XCLIENT      (132, "유효하지 않은 X-CLIENT 헤더 입니다"),
	//INVALID_MOBILE_CODE  (133, "유효하지 않은 인증번호 입니다"),
	INVALID_ENC_STRING   (134, "암복호화에 실패하였습니다"),


	INVALID_PARAM        (135, "파라미터 값이 잘못되었습니다."),

	INCORRECT_USER_INFO(140, "계정 정보가 일치하지 않습니다"),
	//INCORRECT_UUID     (141, "요청 UUID 가 일치하지 않습니다"),
	
	//NOT_SUPPORTED_OS (150, "지원하는 모바일 클라이언트 OS 타입은 'android' 또는 'ios' 입니다"),

	// 메뉴관리
	NOT_FOUND_CODE_BY_ID	 (160, "해당하는 코드가 없습니다."),

	INVALID_GOOGLE_TOKEN (900, "구글 로그인 정보가 잘못되었습니다."),
	
	SUCCESS(0, "success"),
	FAIL   (9, "fail"),
	MAIL_FAIL(999, "메일 전송하는데 에러가 발생하였습니다."),

	// 아이디, 패스워드 찾기
	INVALID_EXISTS_USERNAME (140, "입력하신 정보에 대한 아이디가 존재하지 않습니다. "),
	INVALID_EXISTS_PASSWORD (140, "입력하신 정보에 해당하는 사용자가 없습니다! "),
	
	DATABASE_FAILURE_NO_RECORD (190, "대상 데이터가 존재하지 않습니다."),
	
	// MASTER - 200번대 사용
	INVALID_CONTRACT (201, "계약 정보가 존재하지 않습니다."),
	INVALID_DUPE_CONTRACT (202, "계약을 특정할 수 없습니다.둘 이상의 계약 정보가 존재합니다."),
	NOT_FOUND_DUTY_WAGE (203, "소프트웨어 기술자 평균 임금 데이터가 선행적으로 등록되어 있지 않습니다."),
	NOT_FOUND_STD_UNIT_PRICE (204, "표준단가 데이터가 선행적으로 등록되어 있지 않습니다."),
	INVALID_SLA (205, "SLA 정보가 존재하지 않습니다.");
	
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
