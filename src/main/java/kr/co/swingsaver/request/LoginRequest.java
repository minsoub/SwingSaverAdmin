package kr.co.swingsaver.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="로그인 Request 데이터")
public class LoginRequest {
	
	@Schema(name="메일주소", required=true, example="minsoub@gamil.com")
	public String email;
	
	@Schema(name="패스워드", required=true, example="xxxxxxxxxxxx")
	public String password;
}
