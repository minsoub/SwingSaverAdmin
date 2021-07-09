package kr.co.swingsaver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
@Table(name = "SWADM")
@Schema(description = "관리자정보")
public class AdminEntity {
    @Schema(description = "관리자No.", example = "123")
    @Id
    @Column(name = "id")    
    public long id;

    @Schema(description = "메일주소", example = "mjoung@hist.co.kr")
    @Column(name = "email") 
    public String email;

    @Schema(description = "패스워드", example = "1234")
    @Column(name = "password") 
    public String password;
    
    @Schema(description = "성명", example = "admin")
    @Column(name = "name") 
    public String name;
    
    @Schema(description = "생성일자", example = "20210101120000")
    @Column(name = "createdate") 
    public String createdate;
}
