package kr.co.swingsaver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.swingsaver.entity.IUserInfo;
import kr.co.swingsaver.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	/**
	 * 사용자를 선택할 수 있는 콤보박스를 구성하기 위한 데이터 조회
	 * @return
	 */
    @Query(nativeQuery = true,
            value = "select t.id as id, t.email as email, t.firstname as firstname, t.lastname as lastname  from swuser t order by t.email")
    List<IUserInfo> findUserAllList();

}
