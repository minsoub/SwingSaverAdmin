package kr.co.swingsaver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.swingsaver.model.GroupMemberPK;
import lombok.Data;

@Data
@Entity
@Table(name = "SWGROUPMEMBER")
@IdClass(GroupMemberPK.class)
@Schema(description = "그룹멤버정보")
public class GroupMemberEntity {
    @Schema(description = "Group ID.", example = "123")
    @Id
    @Column(name = "groupid")    
    public String groupid;
    
    @Schema(description = "Member ID.", example = "123")
    @Id  
    @Column(name = "memberid")    
    public Integer memberid;
    
    @Schema(description = "멤버 타입", example = "A")
    @Column(name = "membertype") 
    public String membertype;
    
    @Schema(description = "그룹 멤버 상태", example = "Y")
    @Column(name = "status") 
    public String status;
}
