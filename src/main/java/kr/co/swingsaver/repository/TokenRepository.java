package kr.co.swingsaver.repository;

import org.springframework.data.repository.CrudRepository;

import kr.co.swingsaver.entity.TokenEntity;

public interface TokenRepository  extends CrudRepository<TokenEntity, String> {
    TokenEntity findByAccesstoken(String accesstoken);  // accesstoken은 필드명이 되어야 한다.
}
