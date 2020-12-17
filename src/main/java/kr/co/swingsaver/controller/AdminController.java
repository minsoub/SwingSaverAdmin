package kr.co.swingsaver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swingsaver.request.LoginRequest;
import kr.co.swingsaver.response.AuthResponse;
import kr.co.swingsaver.service.LoginService;
import kr.co.swingsaver.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/api/v1/admin")
@Tag(name="관리자 로그인", description="[system-001] 관리자 로그인")
public class AdminController {
	@Value("${user.data.uploadpath}")
    private String uploadPath;
	
	@Autowired 
	private LoginService service;
	
    @GetMapping("/")
    public String index()
    {
    	
    	return "admin/index";
    }
    
    
    @Operation(summary = "관리자 로그인", description = "관리자 로그인 처리")
    @PostMapping("/login")
    public ResponseEntity<?> loginProcess(@RequestBody LoginRequest request)
	{
    	AuthResponse response = AuthResponse.newInstance();
    	
    	log.debug("login process start...");
    	
    	int result = -1;
    	
    	try {
    		response.addData("data", result);
    		
    		return response.build();
    	}catch(Exception e) {
    		log.error("printStackTrace : ", e);
			return response.build(ResponseCode.FAIL, e);
    	}    	
	}
}
