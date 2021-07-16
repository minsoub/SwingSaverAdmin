package kr.co.swingsaver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import kr.co.swingsaver.controller.GroupController;
import kr.co.swingsaver.dto.GroupListDto;
import kr.co.swingsaver.entity.GroupEntity;
import kr.co.swingsaver.entity.GroupMemberEntity;
import kr.co.swingsaver.model.GroupMemberPK;
import kr.co.swingsaver.repository.GroupListRepositorySupport;
import kr.co.swingsaver.repository.GroupMemberRepository;
import kr.co.swingsaver.repository.GroupRepository;
import kr.co.swingsaver.request.GroupDelRequest;
import kr.co.swingsaver.request.GroupRequest;
import kr.co.swingsaver.response.GroupResponse;
import lombok.val;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class GroupService {
    @Autowired
    private GroupRepository repository;
    
    @Autowired
    private GroupMemberRepository memberRepository;
    
    @Autowired
    private GroupListRepositorySupport support;

    @Autowired
    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }
    /**
     * 그룹 리스트 조회
     * @return
     */
    public GroupResponse search() {
    	List<GroupEntity> result = this.repository.findAll(Sort.by(Sort.Direction.ASC, "id"));  
    	
    	var res = new GroupResponse();
    	res.setList(result);
        return res;
    }
    
    public GroupResponse list() {
    	List<GroupListDto> result = this.support.findAllGroupList();
    	List<GroupListDto> result2 = this.support.findAllGroupAdminList();
    	var res = new GroupResponse();
    	res.setGrouplist(result);
    	res.setGroupadminlist(result2);
    	   	    	
        return res;
    }
    
    /**
     * 그룹 등록 및 등록된 그룹 수정
     * @param request
     * @return
     */
    public GroupEntity save(GroupRequest request) {
        String loggedInId = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAlready = repository.existsById(request.getId());

        val group = repository.findById(request.getId()).orElseGet(GroupEntity::new);
        
        group.setId(isAlready ? request.getId() : String.valueOf(repository.getMaxGroupId()));
        group.setGroupname(request.getGroupname());
        group.setRegion(ObjectUtils.isEmpty(request.getRegion())? null: request.getRegion());
        group.setGrouptype(request.getGrouptype());
        group.setAddress(ObjectUtils.isEmpty(request.getAddress())? null: request.getAddress());
        group.setPhone(ObjectUtils.isEmpty(request.getPhone())?null: request.getPhone());
        group.setQuota(request.getQuota());
        group.setStoragespace(request.getStoragespace());
        group.setStartdate(ObjectUtils.isEmpty(request.getStartdate()) ? null: request.getStartdate());
        group.setEnddate(ObjectUtils.isEmpty(request.getEnddate()) ? null: request.getEnddate());
        group.setGroupadminid(ObjectUtils.isEmpty(request.getGroupadminid()) ? null: request.getGroupadminid());
        group.setRegisterdate(isAlready ? group.getRegisterdate() : null);
        group.setDel_yn(ObjectUtils.isEmpty(request.getDel_yn()) ? "N" : request.getDel_yn());

        return repository.save(group);
    }
    /**
     * 그룹 멤버 회원(관리자)을 저장한다. 
     * 그룹 등록/수정/삭제 시 수행된다. 
     * 
     * @param request
     * @param entity
     * @return
     */
    public GroupMemberEntity save(GroupRequest request, GroupEntity entity) {
    	GroupMemberPK id = new GroupMemberPK();
    	id.setGroupid(request.getId());
    	id.setMemberid(request.getGroupadminid());
    	
    	val member = memberRepository.findById(id).orElseGet(GroupMemberEntity::new);
    	
    	member.setGroupid(entity.id);
    	member.setMemberid(entity.groupadminid);
    	member.setMembertype("A");
    	member.setStatus("Y");

    	return memberRepository.save(member);
    }
    
    @Transactional
    public boolean delete(GroupDelRequest[] req) {

    	for (GroupDelRequest request: req) {
	        boolean isAlready = repository.existsById(request.getId());
	        log.debug("isAlready => " + isAlready);
	        System.out.println("isAlready => " + isAlready);
	        if (!isAlready) {
	        	return false;
	        }else {
	        	val group = repository.findById(request.getId()).orElseThrow();  //  .orElseGet(GroupEntity::new);
	        	
	            group.setId(request.getId());
	            group.setGroupname(request.getGroupname());
	            group.setRegion(ObjectUtils.isEmpty(request.getRegion())? null: request.getRegion());
	            group.setGrouptype(request.getGrouptype());
	            group.setAddress(ObjectUtils.isEmpty(request.getAddress())? null: request.getAddress());
	            group.setPhone(ObjectUtils.isEmpty(request.getPhone())?null: request.getPhone());
	            group.setQuota(request.getQuota());
	            group.setStoragespace(request.getStoragespace());
	            group.setStartdate(ObjectUtils.isEmpty(request.getStartdate()) ? null: request.getStartdate());
	            group.setEnddate(ObjectUtils.isEmpty(request.getEnddate()) ? null: request.getEnddate());
	            group.setGroupadminid(request.getGroupadminid());
	            group.setRegisterdate(isAlready ? group.getRegisterdate() : null);
	            group.setDel_yn("Y");
	            log.debug(group.toString());
	            System.out.println(group.toString());
	
	            repository.save(group);
	        }	        
    	}
    	return true;
    }
}
