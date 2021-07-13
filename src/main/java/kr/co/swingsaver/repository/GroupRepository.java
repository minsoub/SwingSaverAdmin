package kr.co.swingsaver.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.swingsaver.entity.GroupEntity;
import kr.co.swingsaver.entity.IUserInfo;


@Repository
public interface GroupRepository  extends JpaRepository<GroupEntity, String> {

	List<GroupEntity> findAll(Sort s);
	
    @Query(nativeQuery = true,
            value = "select to_number(max(id))+1 from swgroup where id != 'A001'")
    int getMaxGroupId();
		
}
