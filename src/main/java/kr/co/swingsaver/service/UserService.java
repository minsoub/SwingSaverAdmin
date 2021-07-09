package kr.co.swingsaver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.swingsaver.entity.IUserInfo;
import kr.co.swingsaver.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    /**
     * 사용자 리스트를 조회해서 콤보박스를 구성하기 위한 조회
     * 
     * @return
     */
    public List<IUserInfo> findAllUser() {
        return repository.findUserAllList();
    }
}
