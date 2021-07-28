package kr.co.swingsaver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.swingsaver.entity.GroupMemberEntity;
import kr.co.swingsaver.model.GroupMemberPK;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, GroupMemberPK> {
	
	//List<GroupMemberEntity> findAll(GroupMemberPK param);
}
