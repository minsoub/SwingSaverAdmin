package kr.co.swingsaver.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swingsaver.request.ProInfoRequest;
import kr.co.swingsaver.response.AuthResponse;
import kr.co.swingsaver.response.MarketProListResponse;
import kr.co.swingsaver.service.MarketProService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/pro")
@Tag(name="Market Pro 관리", description="Market Pro 관리")
public class MarketProController {

	final MarketProService service;
	
    @Operation(summary = "리스트 Market Pro 조회", description = "리스트 Market Pro 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market Pro 리스트 조회", content = @Content(schema = @Schema(implementation = MarketProListResponse.class)))
    })  
	@GetMapping(value="/prolist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> prolist() {
		return ResponseEntity.ok(service.list());
	}
    
    @Operation(summary = "Market Pro 저장", description = "Market Pro 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market Pro 저장", content = @Content(schema = @Schema(implementation = AuthResponse.class)))
    })  
    @PostMapping(value="/prosave")
	public ResponseEntity<?> prosave(@Parameter(description = "Market Pro 정보", required = true, schema = @Schema(implementation = ProInfoRequest.class))
	@RequestParam ProInfoRequest request) throws Exception {
		
		return ResponseEntity.ok(service.save(request));
	}
}
