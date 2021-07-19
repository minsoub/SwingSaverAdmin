package kr.co.swingsaver.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
@DynamicInsert			// Insert시 null 필드 제외  @DynamicUpdate => update시 null인 필드 제외
@Table(name = "SWUSER")
@Schema(description = "사용자정보")
@SequenceGenerator(
        name="USER_SEQ_GEN",        //시퀀스 제너레이터 이름
        sequenceName="SWUSER_SEQ",  //시퀀스 이름
        initialValue=1,             //시작값
        allocationSize=1            //메모리를 통해 할당할 범위 사이즈
        )
public class UserEntity {
    @Schema(description = "User id", example = "123")
    @Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE,   //사용할 전략을 시퀀스로  선택
            generator="USER_SEQ_GEN"            //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정        
            )    
    @Column(name = "id")    
    public Integer id;
    
    @Schema(description = "메일주소", example = "minsoub@gmail.com")
    @Column(name = "email") 
    public String email;
    
    @Schema(description = "First Name", example = "Joung")
    @Column(name = "firstname") 
    public String firstname;
    
    @Schema(description = "Last Name", example = "minsoub")
    @Column(name = "lastname") 
    public String lastname;
    
    @Schema(description = "Date of ", example = "2020-01-1")
    @Column(name = "dob") 
    public Date dob;
    
    @Schema(description = "성별 ", example = "M")
    @Column(name = "gender") 
    public Date gender;
    
    @Schema(description = "사진 ", example = "바이너리 파일")
    @Lob
    @Column(name = "photo") 
    public Blob photo;
    
    @Schema(description = "전화번호 ", example = "01027631100")
    @Column(name = "phone") 
    public String phone;
    
    @Schema(description = "그룹멤버 여부 ", example = "N")
    @Column(name = "groupmember") 
    public String groupmember;
    
    @Schema(description = "포인트 ", example = "0")
    @Column(name = "availablepoints") 
    public Integer availablepoints;
    
    @Schema(description = "할당공간 ", example = "600")
    @Column(name = "userspace") 
    public Integer userspace;
    
    @Schema(description = "생성일자 ", example = "2020-01-01")
    @Column(name = "createdate") 
    public Date createdate;
    
    @Schema(description = "사진 타입 ", example = "image/jpg")
    @Column(name = "phototype") 
    public String phototype;
    
    @Schema(description = "평균타수 ", example = "5")
    @Column(name = "average") 
    public Integer average;
    
    @Schema(description = "핸디캡", example = "20")
    @Column(name = "handicap") 
    public Integer handicap;
    
    @Schema(description = "비거리", example = "300")
    @Column(name = "distance") 
    public Integer distance;
    
}
