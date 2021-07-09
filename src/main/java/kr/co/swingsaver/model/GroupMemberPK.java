package kr.co.swingsaver.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class GroupMemberPK implements Serializable{
	private static final long serialVersionUID = 7300000432050013455L;
	private String groupid;
	private long memberid;
}
