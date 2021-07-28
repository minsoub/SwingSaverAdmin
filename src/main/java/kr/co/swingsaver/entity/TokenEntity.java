package kr.co.swingsaver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
@Table(name = "tokendata")
@Schema(description = "토큰 정보")
public class TokenEntity {
    @Id
    public String memberid;

    @Column(name = "accesstoken") 
    public String accesstoken;

    @Column(name = "expiredate") // 단위 초
    public long expiredate;
}
