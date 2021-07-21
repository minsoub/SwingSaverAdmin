package kr.co.swingsaver.request;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "마켓프로정보")
public class ProInfoRequest {
    @Schema(description = "Market Pro ID", example = "123")
    public Integer id;
    
    @Schema(description = "User ID", example = "123")
    public Integer userid;
        
    @Schema(description = "지역", example = "KR")
    public String region;
    
    @Schema(description = "Lesson Price", example = "123")
    public Integer lessonprice;
    
    @Schema(description = "Rating", example = "3.2")
    public Float rating;    
    
    @Schema(description = "Pro Level", example = "123")
    public Integer prolevel;
    
    @Schema(description = "프로필", example = "프로필 정보")
    public String profile;
    
    @Schema(description = "레슨방향", example = "레슨방향")
    public String description;
    
    @Schema(description = "프로필 이미지", example = "프로필 이미지 정보")
    public String profile_img;
    
    @Schema(description = "이전 프로필 이미지 정보", example = "이전 프로필 이미지 정보")
    public String org_profile_file;
    
    @Schema(description = "사용 여부", example = "Y")
    public String use_yn;
}
