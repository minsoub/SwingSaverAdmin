package kr.co.swingsaver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.swingsaver.model.MarketProPK;
import lombok.Data;

@Data
@Entity
@Table(name = "swmarketpro")
@Schema(description = "마켓프로정보")
@IdClass(MarketProPK.class)
public class MarketProEntity {

    @Schema(description = "마켓프로 아이디", example = "123")
    @Id
    @Column(name = "id")    
    public Integer id;
    
    @Schema(description = "사용자 아이디", example = "123")
    @Id
    @Column(name = "userid")    
    public Integer userid; 
    
    @Schema(description = "지역", example = "KR")
    @Column(name = "region") 
    public String region;
    
    @Schema(description = "레슨가격", example = "1000")
    @Column(name = "lessonprice")  
    public Integer lessonprice;
    
    @Schema(description = "Rating", example = "1.4")
    @Column(name = "rating")  
    public Float rating;
    
    @Schema(description = "프로레벨", example = "1")
    @Column(name = "prolevel")  
    public Integer prolevel;  
    
    @Schema(description = "프로필", example = "프로필 정보")
    @Column(name = "profile") 
    public String profile;  
    
    @Schema(description = "레슨방향", example = "레슨방향 설명")
    @Column(name = "description") 
    public String description;    
    
    @Schema(description = "프로필 이미지", example = "프로필 이미지 URL")
	@Column(name="profile_img", nullable=false, length=200)
    public String profile_img;
    
    @Schema(description = "사용여부", example = "Y")
	@Column(name="use_yn", nullable=false, length=1)
    public String use_yn;

    
}
