package kr.co.swingsaver.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공통 사용자 아이디/메일주소/이름")
public interface IUserInfo {
    @Schema(description = "사용자 아이디", example = "100")
    String getId();

    @Schema(description = "사용자 성", example = "정")
    String getLastname();
    
    @Schema(description = "사용자 이름", example = "민섭")
    String getFirstname();
    
    @Schema(description = "메일주소", example = "minsoub@gamil.com")
    String getEmail();
}
