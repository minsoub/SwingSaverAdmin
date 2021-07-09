package kr.co.swingsaver.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "그룹정보")
public class GroupDto {
    @Schema(description = "Group No.", example = "123")
    public String id;
    
    @Schema(description = "지역", example = "KR")
    public String region;
    
    @Schema(description = "그룹명", example = "아카데미그룹")
    public String groupname;
    
    @Schema(description = "그룹타입", example = "A")  
    public String grouptype;
    
    @Schema(description = "주소", example = "서울 영등포") 
    public String address;
    
    @Schema(description = "전화번호", example = "01033322121")  
    public String phone;
    
    @Schema(description = "멤버인원", example = "1")
    public int quota;
    
    @Schema(description = "사용량", example = "1")  
    public int storagespace;
    
    @Schema(description = "상태", example = "Y")
    public String status;
    
    @Schema(description = "시작일자", example = "2020-02-01")
    public Date startdate;
    
    @Schema(description = "종료일자", example = "2020-12-01")
    public Date enddate;
    
    @Schema(description = "그룹관리자아이디", example = "32")
    public Long groupadminid;
    
    @Schema(description = "등록일자", example = "2020-12-01")
    public Date registerdate;
    
    @Schema(description = "삭제여부", example = "N")
    public String del_yn;
}
