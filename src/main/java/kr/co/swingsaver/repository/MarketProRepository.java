package kr.co.swingsaver.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.swingsaver.entity.MarketProEntity;

@Repository
public interface MarketProRepository extends JpaRepository<MarketProEntity, String> {

	List<MarketProEntity> findAll(Sort sort);
}
