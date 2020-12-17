package kr.co.swingsaver.response;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import kr.co.swingsaver.utils.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
	public int    status    = 0;
	public String message   = "";
	public String timestamp;

	public Object response;
	
	public static AuthResponse newInstance() {
		AuthResponse response = new AuthResponse();
		
		response.status    = ResponseCode.SUCCESS.status;
		response.message   = ResponseCode.SUCCESS.message;
		response.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		response.response  = new HashMap<String, Object>();
		
		return response;
	}
	
	public static AuthResponse newInstance(ResponseCode code) {
		AuthResponse response = new AuthResponse();
		
		if (code != null) {
			response.status  = code.status;
			response.message = code.message;
		}else {
			response.status  = ResponseCode.SUCCESS.status;
			response.message = ResponseCode.SUCCESS.message;
		}
		response.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		response.response  = new HashMap<String, Object>();
		
		return response;
	}
	
	public AuthResponse setResponseCode(ResponseCode code) {
		this.status  = code.status;
		this.message = code.message;
		
		return this;
	}
	
	public AuthResponse appendStatus(String appendString) {
		this.message += appendString;
		
		return this;
	}
	
	public ResponseEntity<AuthResponse> build() {
		this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		
		return new ResponseEntity<>(this, HttpStatus.OK);
	}
	
	public ResponseEntity<AuthResponse> build(ResponseCode code) {
		if (code != null) {
			this.status  = code.status;
			this.message = code.message;
		}
		this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		
		return new ResponseEntity<>(this, HttpStatus.OK);
	}
	/**
	 * 에러 메세지의 문자열을 출력한다. 
	 * 
	 * @param code
	 * @param e
	 * @return
	 */
	public ResponseEntity<AuthResponse> build(ResponseCode code, Exception e) {
		if (code != null) {
			this.status  = code.status;
			this.message = e.getMessage();
		}
		this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		
		return new ResponseEntity<>(this, HttpStatus.OK);
	}
	

    public ResponseEntity<AuthResponse> build(HttpStatus status) {

        this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();

        return new ResponseEntity<>(this, status);
    }

	@SuppressWarnings("unchecked")
	public AuthResponse addData(String key, Object value) {
		if (!ObjectUtils.isEmpty(response) && !(response instanceof HashMap)) return this;
		
		if (ObjectUtils.isEmpty(response)) response = new HashMap<String, Object>();
		
		HashMap<String, Object> data = (HashMap<String, Object>)response;
		if (key.equals("response")) {
			response = value;
		} else {
			if (data.containsKey(key)) data.remove(key);
			data.put(key, value);
		}
		return this;
	}

}
