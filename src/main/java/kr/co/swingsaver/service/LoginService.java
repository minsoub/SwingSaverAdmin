package kr.co.swingsaver.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.swingsaver.entity.TokenEntity;
import kr.co.swingsaver.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class LoginService {
    final TokenRepository repository;

    @Value("${jwt.access-expired}")
    private long ttl;

    @Value("${jwt.prefix}")
    private String REDIS_PREFIX;

    public void save(String username, String accessToken) {

        val data = new TokenEntity();
        data.setMemberid(username);
        data.setAccesstoken(accessToken);
        data.setExpiredate(System.currentTimeMillis() + ttl);
        repository.save(data);
    }
    
    public void deleteById(String username) {
        repository.deleteById(username);
    }

    public boolean existsByAccessToken(String username) {
        return repository.existsById(username);
    }

    public Optional<TokenEntity> findById(String username) {
        val data = repository.findById(username);
        return data;
    }
    
}
