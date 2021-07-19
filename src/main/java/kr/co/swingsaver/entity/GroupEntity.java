package kr.co.swingsaver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
@Table(name = "SWGROUP")
@Schema(description = "그룹정보")
public class GroupEntity {
    @Schema(description = "Group No.", example = "123")
    @Id
    @Column(name = "id")    
    public String id;
    
    @Schema(description = "지역", example = "KR")
    @Column(name = "region") 
    public String region;
    
    @Schema(description = "그룹명", example = "아카데미그룹")
    @Column(name = "groupname") 
    public String groupname;
    
    @Schema(description = "그룹타입", example = "A")
    @Column(name = "grouptype")     
    public String grouptype;
    
    @Schema(description = "주소", example = "서울 영등포")
    @Column(name = "address")   
    public String address;
    
    @Schema(description = "전화번호", example = "01033322121")
    @Column(name = "phone")  
    public String phone;
    
    @Schema(description = "멤버인원", example = "1")
    @Column(name = "quota")  
    public int quota;
    
    @Schema(description = "사용량", example = "1")
    @Column(name = "storagespace")      
    public int storagespace;
    
    @Schema(description = "상태", example = "Y")
    @Column(name = "status")   
    public String status;
    
    @Schema(description = "시작일자", example = "2020-02-01")
    @Column(name = "startdate")  
    public Date startdate;
    
    @Schema(description = "종료일자", example = "2020-12-01")
    @Column(name = "enddate")  
    public Date enddate;
    
    @Schema(description = "그룹관리자아이디", example = "32")
    @Column(name = "groupadminid")  
    public Integer groupadminid;
    
    @Schema(description = "등록일자", example = "2020-12-01")
    @Column(name = "registerdate")  
    public Date registerdate;
    
    @Schema(description = "삭제여부", example = "N")
    @Column(name = "del_yn")  
    public String del_yn;
    
}
