package kr.co.swingsaver.response;

import java.util.List;

import kr.co.swingsaver.dto.GroupDto;
import kr.co.swingsaver.dto.GroupListDto;
import kr.co.swingsaver.entity.GroupEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponse extends GroupDto {
	List<GroupEntity> list;
	List<GroupListDto> grouplist;
	List<GroupListDto> groupadminlist;
}
