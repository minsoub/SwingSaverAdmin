package kr.co.swingsaver.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.swingsaver.dto.MarketProListDto;
import kr.co.swingsaver.entity.MarketProEntity;
import kr.co.swingsaver.model.MarketProPK;
import kr.co.swingsaver.repository.MarketProListRepositorySupport;
import kr.co.swingsaver.repository.MarketProRepository;
import kr.co.swingsaver.request.ProInfoRequest;
import kr.co.swingsaver.response.AuthResponse;
import kr.co.swingsaver.response.MarketProListResponse;
import kr.co.swingsaver.response.MarketProResponse;
import kr.co.swingsaver.utils.CommonUtil;
import kr.co.swingsaver.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketProService {

	@Value("${user.data.uploadpath}")
	private String location;
	private Path fileLocation;
	
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
	
	@PostConstruct
	private void init() {
		this.fileLocation = Paths.get(location);
		try {
			Files.createDirectories(this.fileLocation);
		}catch(IOException ex) {
			log.error(ex.getMessage());
		}
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
	
	/**
	 * 마켓 프로 정보 저장
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public AuthResponse save(ProInfoRequest request) throws Exception {
		var res = new AuthResponse();

		MarketProPK pk = new MarketProPK();
		pk.setId(request.id);
		pk.setUserid(request.userid);
		boolean isAlready = this.repository.existsById(pk);
		var entity = this.repository.findById(pk).orElseGet(MarketProEntity::new);
		
		entity.id = isAlready ? request.id : this.repository.getMaxMarketProId();
		entity.userid = request.userid;
		entity.lessonprice = request.lessonprice;
		entity.prolevel = request.prolevel;
		entity.profile = request.profile;
		entity.rating = request.rating;
		entity.region = request.region;
		entity.description = request.description;
		entity.use_yn = request.use_yn;
		if (request.org_profile_file == null) {
			entity.profile_img = request.profile_img;
		}else {
			entity.profile_img = request.org_profile_file;
		}
		log.debug(entity.toString());
		
		MarketProEntity r = this.repository.save(entity);
		if (r == null) res.setResponseCode(ResponseCode.FAIL);
		else res.setResponseCode(ResponseCode.SUCCESS);
		
		return res;
	}
}
