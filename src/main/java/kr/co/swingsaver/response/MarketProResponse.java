package kr.co.swingsaver.response;

import java.util.List;

import kr.co.swingsaver.entity.MarketProEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketProResponse {

	List<MarketProEntity> list;
}
