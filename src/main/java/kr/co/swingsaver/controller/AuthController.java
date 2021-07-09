package kr.co.swingsaver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swingsaver.request.AuthRequest;
import kr.co.swingsaver.response.AuthResponse;
import kr.co.swingsaver.security.AuthUserDetails;
import kr.co.swingsaver.security.JwtTokenUtil;
import kr.co.swingsaver.service.AuthDetailsService;
import kr.co.swingsaver.service.LoginService;
import kr.co.swingsaver.utils.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@Tag(name="관리자 로그인", description="관리자 로그인")
public class AuthController {

	final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtTokenUtil;    
    final LoginService loginService;
    final AuthDetailsService authService;
    
	
    @Operation(summary = "관리자 로그인", description = "관리자 로그인 처리")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 - access", content = @Content(schema = @Schema(implementation = AuthResponse.class)))
    })    
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginProcess(@Parameter(description = "아이디 패스워드를 보낸다.", required = true, schema = @Schema(implementation = AuthRequest.class)) 
    							@Valid @RequestBody AuthRequest request)
	{
    	AuthResponse response = AuthResponse.newInstance();    	
    	log.debug("login process start...");
    	try {
    		
    		authenticate(request.getUsername(), request.getPassword());
    		String accesstoken = createAuthenticationToken(request.getUsername());
            //return ResponseEntity.ok(createAuthenticationToken(request.getUsername()));
                        
    		response.addData("data", accesstoken);
    		
    		return response.build();
    	}catch(Exception e) {
    		log.error("printStackTrace : ", e);
			return response.build(ResponseCode.FAIL, e);
    	}    	
	}
    
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private String createAuthenticationToken(String mberId) throws UsernameNotFoundException {
    	AuthUserDetails userDetails = (AuthUserDetails) authService.loadUserByUsername(mberId);
    	String accessToken = jwtTokenUtil.generateToken(userDetails);
        loginService.save(mberId, accessToken);
        
        return accessToken;
    }
}
