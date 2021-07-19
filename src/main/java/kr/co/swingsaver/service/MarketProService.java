package kr.co.swingsaver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.co.swingsaver.dto.MarketProListDto;
import kr.co.swingsaver.entity.MarketProEntity;
import kr.co.swingsaver.repository.MarketProListRepositorySupport;
import kr.co.swingsaver.repository.MarketProRepository;
import kr.co.swingsaver.response.MarketProListResponse;
import kr.co.swingsaver.response.MarketProResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketProService {

	@Autowired
	private MarketProRepository repository;
	@Autowired
	private MarketProListRepositorySupport supportRepository;
	
	@Autowired
	public MarketProService(MarketProRepository repository, MarketProListRepositorySupport supportRepository)
	{
		this.repository = repository;
		this.supportRepository = supportRepository;
	}
	
	/**
	 * Market Pro 리스트를 조회한다. 
	 * 
	 * @return
	 */
	public MarketProResponse search() {
		List<MarketProEntity> list = this.repository.findAll(Sort.by(Sort.Direction.ASC, "id"));  
		var res = new MarketProResponse();
		res.setList(list);
		return res;
	}
	
	/**
	 * Market Pro 리스트를 조회한다. 
	 * 필요한 데이터를 조합해서 리턴한다. 
	 * 
	 * @return
	 */
	public MarketProListResponse list() {
		List<MarketProListDto> list = this.supportRepository.findAllProList();
		var res = new MarketProListResponse();
		res.setList(list);
		return res;
	}
}
