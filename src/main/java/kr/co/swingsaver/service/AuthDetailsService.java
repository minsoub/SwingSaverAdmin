package kr.co.swingsaver.service;


import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.swingsaver.entity.AdminEntity;
import kr.co.swingsaver.entity.TokenEntity;
import kr.co.swingsaver.model.RoleType;
import kr.co.swingsaver.repository.AdminRepository;
import kr.co.swingsaver.repository.TokenRepository;
import kr.co.swingsaver.security.AuthUserDetails;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthDetailsService implements UserDetailsService {
	
	private TokenRepository repository;
	private AdminRepository repoAdmin;
	
    @Value("${jwt.access-expired}")
    private long ttl;

    @Value("${jwt.prefix}")
    private String REDIS_PREFIX;
    
    @Autowired
    public AuthDetailsService(TokenRepository repository, AdminRepository repoAdmin) {
        this.repository = repository;
        this.repoAdmin = repoAdmin;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TokenEntity userInfo = repository.findById(username).orElse(null);    // token저장 테이블에서 조회 : username => email 주소이다.
        if (userInfo == null) {
            // token table 내에 사용자 정보가 없다면 실제로 db를 조회 함
            AdminEntity user = repoAdmin.findByEmail(username);  // .orElseThrow();
            if (user == null) {
            	throw new UsernameNotFoundException("Not exist user...");
            }

            AuthUserDetails iUser = new AuthUserDetails(user.email, user.password, user.name, user.id, getAuthority(user));
            // 정상적인 사용자이면, redis에 사용자 정보 put
            // redisService.save(user);
            return iUser;

        } else {
            // token 내에 사용자 정보가 존재한다면 
        	// token 만료일자 계산
            boolean isValideUser = canLogin(userInfo.expiredate);
            if (!isValideUser) {
            	repository.deleteById(username);
                throw new UsernameNotFoundException("계정정보를 찾을 수 없습니다. ::" + username);
            } else {
            	AdminEntity user = repoAdmin.findByEmail(username);  // .orElseThrow();
                return new AuthUserDetails(user.email, user.password, user.name, user.id, getAuthority(user));
            }
        }
	}
	
    private boolean canLogin(long expiredate) {
        val today = System.currentTimeMillis();
        try {
            return (expiredate > today);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
        
    private Set getAuthority(AdminEntity user) {
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ RoleType.ADMIN));
            
//            if (user.empRoleFlag.equals("S")) {
//                // 관리자 여부 판단
//                authorities.add(new SimpleGrantedAuthority("ROLE_"+ RoleType.ADMIN));
//                authorities.add(new SimpleGrantedAuthority("ROLE_"+ RoleType.LOGGINED));
//                authorities.add(new SimpleGrantedAuthority("ROLE_"+ RoleType.SUPERADMIN));
//            } else if (user.empRoleFlag.equals("M")) {
//                // 관리자 여부 판단
//                authorities.add(new SimpleGrantedAuthority("ROLE_"+ RoleType.ADMIN));
//                authorities.add(new SimpleGrantedAuthority("ROLE_"+ RoleType.LOGGINED));
//            } else {
//                authorities.add(new SimpleGrantedAuthority("ROLE_"+ RoleType.LOGGINED));
//            }
        return authorities;
    }
  

}
