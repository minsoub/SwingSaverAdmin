package kr.co.swingsaver.response;

import java.util.List;

import kr.co.swingsaver.dto.MarketProListDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketProListResponse {
	String t;
	List<MarketProListDto> list;
}
