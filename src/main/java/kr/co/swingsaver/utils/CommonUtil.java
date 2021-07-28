package kr.co.swingsaver.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {
	/**
	 * 로그인한 사용자 아이디 리턴
	 * 
	 * @return
	 */
	public static String getLoginUserId()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        UserDetails userDetails = (UserDetails)principal; 
        String reg_id = userDetails.getUsername();
        
        return reg_id;
	}
	
	/**
	 * 파일명 생성 
	 * 
	 * @return
	 */
	public static String generateUniqueFileName() {
	    String filename = "";
	    long millis = System.currentTimeMillis();
	    String rndchars = RandomStringUtils.randomAlphanumeric(16);
	    filename = rndchars + "_" + millis;
	    return filename;
	}
}
