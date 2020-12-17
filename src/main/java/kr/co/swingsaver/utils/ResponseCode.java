package kr.co.swingsaver.utils;

public enum ResponseCode {
	//ALREADY_EXISTS_MOBILENO    (100, "�̹� ��ϵ� mobileNo �Դϴ�"),
	//ALREADY_REQUEST_MOBILE_CODE(101, "�̹� ������ȣ�� �߼۵Ǿ����ϴ�"),
	//ALREADY_EXISTS_UUID        (102, "�̹� ��ϵ� uuid �Դϴ�"),
	ALREADY_EXISTS_USERNAME    (103, "�̹� ��ϵ� ������ �Դϴ�"),
	ALREADY_EXISTS_CODE        (104, "�̹� ��ϵ� �ڵ�� �Դϴ�"),
	
	NOT_FOUND_ROLE_TYPE    (110, "���ǵ��� ���� ROLE_TYPE �Դϴ�"),
	//NOT_FOUND_MOBILENO     (111, "mobileNo �� ã�� �� �����ϴ�"),
	//NOT_FOUND_UUID         (112, "uuid �� ã�� �� �����ϴ�"),
	NOT_FOUND_ACCESS_TOKEN (113, "access_token �� ã�� �� �����ϴ�"),
	NOT_FOUND_REFRESH_TOKEN(114, "refresh_token �� ã�� �� �����ϴ�"),
	NOT_FOUND_USER_INFO    (115, "����� ������ ã�� �� �����ϴ�"),
	
	EXPIRED_ACCESS_TOKEN (120, "access_token �� ����Ǿ����ϴ�"),
	EXPIRED_REFRESH_TOKEN(121, "refresh_token �� ����Ǿ����ϴ�"),

	INVALID_ACCESS_TOKEN (130, "��ȿ���� ���� access_token �Դϴ�"),
	INVALID_REFRESH_TOKEN(131, "��ȿ���� ���� refresh_token �Դϴ�"),
	INVALID_XCLIENT      (132, "��ȿ���� ���� X-CLIENT ��� �Դϴ�"),
	//INVALID_MOBILE_CODE  (133, "��ȿ���� ���� ������ȣ �Դϴ�"),
	INVALID_ENC_STRING   (134, "�Ϻ�ȣȭ�� �����Ͽ����ϴ�"),


	INVALID_PARAM        (135, "�Ķ���� ���� �߸��Ǿ����ϴ�."),

	INCORRECT_USER_INFO(140, "���� ������ ��ġ���� �ʽ��ϴ�"),
	//INCORRECT_UUID     (141, "��û UUID �� ��ġ���� �ʽ��ϴ�"),
	
	//NOT_SUPPORTED_OS (150, "�����ϴ� ����� Ŭ���̾�Ʈ OS Ÿ���� 'android' �Ǵ� 'ios' �Դϴ�"),

	// �޴�����
	NOT_FOUND_CODE_BY_ID	 (160, "�ش��ϴ� �ڵ尡 �����ϴ�."),

	INVALID_GOOGLE_TOKEN (900, "���� �α��� ������ �߸��Ǿ����ϴ�."),
	
	SUCCESS(0, "success"),
	FAIL   (9, "fail"),
	MAIL_FAIL(999, "���� �����ϴµ� ������ �߻��Ͽ����ϴ�."),

	// ���̵�, �н����� ã��
	INVALID_EXISTS_USERNAME (140, "�Է��Ͻ� ������ ���� ���̵� �������� �ʽ��ϴ�. "),
	INVALID_EXISTS_PASSWORD (140, "�Է��Ͻ� ������ �ش��ϴ� ����ڰ� �����ϴ�! "),
	
	DATABASE_FAILURE_NO_RECORD (190, "��� �����Ͱ� �������� �ʽ��ϴ�."),
	
	// MASTER - 200���� ���
	INVALID_CONTRACT (201, "��� ������ �������� �ʽ��ϴ�."),
	INVALID_DUPE_CONTRACT (202, "����� Ư���� �� �����ϴ�.�� �̻��� ��� ������ �����մϴ�."),
	NOT_FOUND_DUTY_WAGE (203, "����Ʈ���� ����� ��� �ӱ� �����Ͱ� ���������� ��ϵǾ� ���� �ʽ��ϴ�."),
	NOT_FOUND_STD_UNIT_PRICE (204, "ǥ�شܰ� �����Ͱ� ���������� ��ϵǾ� ���� �ʽ��ϴ�."),
	INVALID_SLA (205, "SLA ������ �������� �ʽ��ϴ�.");
	
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
