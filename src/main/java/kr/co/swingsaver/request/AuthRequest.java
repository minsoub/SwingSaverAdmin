package kr.co.swingsaver.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="로그인 Request 데이터")
public class AuthRequest {

	@Schema(name="username", required=true, example="minsoub@gamil.com")
	public String username;
	
	@Schema(name="password", required=true, example="xxxxxxxxxxxx")
	public String password;
}
