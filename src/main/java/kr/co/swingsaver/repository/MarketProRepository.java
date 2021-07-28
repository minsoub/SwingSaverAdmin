package kr.co.swingsaver.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.swingsaver.entity.MarketProEntity;
import kr.co.swingsaver.model.MarketProPK;

@Repository
public interface MarketProRepository extends JpaRepository<MarketProEntity, MarketProPK> {

	List<MarketProEntity> findAll(Sort sort);
	
    @Query(nativeQuery = true,
            value = "select to_number(max(id))+1 from SWMARKETPRO")
    int getMaxMarketProId();
}
