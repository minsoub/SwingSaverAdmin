package kr.co.swingsaver.repository;

import static kr.co.swingsaver.entity.QMarketProEntity.marketProEntity;
import static kr.co.swingsaver.entity.QUserEntity.userEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.swingsaver.dto.MarketProListDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MarketProListRepositorySupport {

	private final JPAQueryFactory factory;
	
	public List<MarketProListDto> findAllProList()
	{
		return factory
				.select(Projections.fields(
							MarketProListDto.class, 
							marketProEntity.id,
							marketProEntity.userid,
							marketProEntity.region,
							marketProEntity.lessonprice,
							marketProEntity.rating,
							marketProEntity.prolevel,
							marketProEntity.description,
							marketProEntity.profile,
							marketProEntity.profile_img,
							marketProEntity.use_yn,
							ExpressionUtils.as (
									JPAExpressions.select(userEntity.email)
											.from(userEntity)
											.where(userEntity.id.eq(marketProEntity.userid)),
											"email" 
							),
							ExpressionUtils.as (
									JPAExpressions.select(userEntity.firstname)
											.from(userEntity)
											.where(userEntity.id.eq(marketProEntity.userid)),
											"firstname" 
							),
							ExpressionUtils.as (
									JPAExpressions.select(userEntity.lastname)
											.from(userEntity)
											.where(userEntity.id.eq(marketProEntity.userid)),
											"lastname" 
							)								
						)						
					)
				.from(marketProEntity)
				.where(marketProEntity.use_yn.eq("Y"))
				.orderBy(marketProEntity.id.asc())
				.fetch();
				
	}
}
