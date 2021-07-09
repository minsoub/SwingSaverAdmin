package kr.co.swingsaver.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.swingsaver.entity.GroupEntity;


@Repository
public interface GroupRepository  extends JpaRepository<GroupEntity, String> {

	List<GroupEntity> findAll(Sort s);
	
	
}
