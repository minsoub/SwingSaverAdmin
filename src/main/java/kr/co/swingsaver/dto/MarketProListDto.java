package kr.co.swingsaver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Market Pro 리스트 정보")
public class MarketProListDto {
    @Schema(description = "마켓프로 아이디", example = "123")
    public Integer id;
    
    @Schema(description = "사용자 아이디", example = "123") 
    public Integer userid; 
    
    @Schema(description = "지역", example = "KR")
    public String region;
    
    @Schema(description = "레슨가격", example = "1000") 
    public Integer lessonprice;
    
    @Schema(description = "Rating", example = "1.4")
    public Float rating;
    
    @Schema(description = "프로레벨", example = "1")
    public Integer prolevel;  
    
    @Schema(description = "프로필", example = "프로필 정보")
    public String profile;  
    
    @Schema(description = "레슨방향", example = "레슨방향 설명")
    public String description;    
    
    @Schema(description = "프로필 이미지", example = "프로필 이미지 URL")
    public String profile_img;
    
    @Schema(description = "사용여부", example = "Y")
    public String use_yn;
    
    @Schema(description = "이메일", example = "minsoub@gmail.com")
    public String email;
    
    @Schema(description = "성", example = "정")
    public String firstname;
 
    @Schema(description = "이름", example = "민섭")
    public String lastname;
}
