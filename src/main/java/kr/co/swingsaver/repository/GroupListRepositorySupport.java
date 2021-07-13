package kr.co.swingsaver.repository;

import static kr.co.swingsaver.entity.QGroupEntity.groupEntity;
import static kr.co.swingsaver.entity.QGroupMemberEntity.groupMemberEntity;
import static kr.co.swingsaver.entity.QUserEntity.userEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.swingsaver.dto.GroupListDto;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class GroupListRepositorySupport {

	private final JPAQueryFactory queryFactory;
	
	/**
	 * 그룹 리스트 전체 조회
	 * 
	 * @return
	 */
	public List<GroupListDto> findAllGroupList() {
		
		return queryFactory
				.select(Projections.fields(
						GroupListDto.class, 
						groupEntity.id,
						groupEntity.groupname,
						groupEntity.address,
						groupEntity.del_yn,
						groupEntity.grouptype,
						groupEntity.phone,
						groupEntity.quota,
						groupEntity.region,
						groupEntity.storagespace,
						groupEntity.status,
						groupEntity.startdate,
						groupEntity.enddate,
						groupEntity.registerdate,
						ExpressionUtils.as (
								JPAExpressions.select(groupMemberEntity.memberid.count())
										.from(groupMemberEntity)
										.where(groupMemberEntity.groupid.eq(groupEntity.id), groupMemberEntity.membertype.ne("A")),
										"membercount" 
							)
						/*,
						ExpressionUtils.as(
								JPAExpressions.select(userEntity.lastname)
								.from(userEntity)								
								.leftJoin(groupMemberEntity)
								.on(groupMemberEntity.groupid.eq(groupEntity.id), userEntity.id.eq(groupMemberEntity.memberid), 
										groupMemberEntity.membertype.eq("A"))
								.limit(1), 
								"lastname"
						    ),
						ExpressionUtils.as(
								JPAExpressions.select(userEntity.firstname)
								.from(userEntity)
								.leftJoin(groupMemberEntity)
								.on(groupMemberEntity.groupid.eq(groupEntity.id), userEntity.id.eq(groupMemberEntity.memberid), 
										groupMemberEntity.membertype.eq("A"))
								.limit(1),
								"firstname"
						    ),		
						ExpressionUtils.as(
								JPAExpressions.select(userEntity.email)
								.from(userEntity)
								.leftJoin(groupMemberEntity)
								.on(groupMemberEntity.groupid.eq(groupEntity.id), userEntity.id.eq(groupMemberEntity.memberid), 
										groupMemberEntity.membertype.eq("A"))
								.limit(1),
								"email"
						    ),		
						ExpressionUtils.as(
								JPAExpressions.select(userEntity.id)
								.from(userEntity)
								.leftJoin(groupMemberEntity)
								.on(groupMemberEntity.groupid.eq(groupEntity.id), userEntity.id.eq(groupMemberEntity.memberid), 
										groupMemberEntity.membertype.eq("A"))
								.limit(1),
								"groupadminid"
						    )	*/						

						)
					)						
				.from(groupEntity)
				.where(groupEntity.del_yn.ne("Y"))
				.orderBy(groupEntity.id.asc())
				.fetch();
	}
	
//	,Expressions.list(userEntity.lastname, userEntity.firstname, userEntity.email).in(
//			JPAExpressions.select(userEntity.lastname, userEntity.firstname, userEntity.email)
//			.from(groupMemberEntity)
//			.leftJoin(userEntity)
//			.where(groupMemberEntity.groupid.eq(groupEntity.id), userEntity.id.eq(groupMemberEntity.memberid), 
//					groupMemberEntity.membertype.ne("A"))
//			.limit(1)
//	    )
	
	/**
	 * 그룹의 관리자 리스트 조회 
	 * 중복으로 관리자 아이디가 조회될 수 있다. 
	 * @return
	 */
	public List<GroupListDto> findAllGroupAdminList() {
		
		return queryFactory
				.select(Projections.fields(
						GroupListDto.class, 
						groupEntity.id,
						groupEntity.groupname,
						groupEntity.address,
						groupEntity.del_yn,
						groupEntity.grouptype,
						groupEntity.phone,
						groupEntity.quota,
						groupEntity.region,
						groupEntity.storagespace,
						groupEntity.status,
						groupEntity.startdate,
						groupEntity.enddate,
						groupEntity.registerdate,
						ExpressionUtils.as (
								JPAExpressions.select(groupMemberEntity.memberid.count())
										.from(groupMemberEntity)
										.where(groupMemberEntity.groupid.eq(groupEntity.id), groupMemberEntity.membertype.ne("A")),
										"membercount" 
							),
						ExpressionUtils.as (					
								userEntity.id, "groupadminid"
							),
						ExpressionUtils.as (					
								userEntity.firstname, "firstname"
							),
						ExpressionUtils.as (					
								userEntity.lastname, "lastname"
							),
						ExpressionUtils.as (					
								userEntity.email, "email"
							)												
					)
				)						
				.from(groupEntity)
				.leftJoin(groupMemberEntity)
				.on(groupMemberEntity.groupid.eq(groupEntity.id), groupMemberEntity.membertype.eq("A"))
				.leftJoin(userEntity)
				.on(userEntity.id.eq(groupMemberEntity.memberid), groupMemberEntity.membertype.eq("A"))
				.where(groupEntity.del_yn.ne("Y"), groupMemberEntity.groupid.eq(groupEntity.id))
				.fetch();
	}
}
